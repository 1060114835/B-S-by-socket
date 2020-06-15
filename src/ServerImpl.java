import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 服务端Server， 里面用到了线程池来处理
 */
public class ServerImpl implements Server {


    private ServerImpl() {
        try {
            serverSocket = new ServerSocket(6868);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int clientId = 1;

    private static ServerImpl mInstance;

    //服务端的线程池，
    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    private ServerSocket serverSocket;

    private final LinkedList<ClientBean> clientBeans = new LinkedList<>();

    @Override
    public void accept() {
        System.out.println("Server start to accept");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.execute(() -> {
                    try {
                        System.out.println(clientId + "号机已连接");
                        BufferedWriter out = new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream()));
                        out.write("Server: 已建立连接" + "\n"
                                + "Server: 你的客户Id为:" + clientId + "\n");
                        out.flush();
                        ClientBean clientBean = new ClientBean(socket, clientId);
                        threadPool.execute(new ServerReceiver(clientBeans, clientBean));
                        clientId++;
                    } catch (IOException e) {
                        System.out.println("Server error，exit!!");
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ServerImpl getServer() {
        Menu.showServerMenu();
        if (mInstance == null) {
            mInstance = new ServerImpl();
        }
        return mInstance;
    }

    public static void main(String[] args) {
        ServerImpl.getServer().accept();
    }


}
