import java.io.*;
import java.net.Socket;


/**
 * 客户端Client: 客户端有两个线程，主线程负责
 */
public class ClientImpl implements Client{

    public ClientImpl() {
        Menu.showClientMenu();
    }

    @Override
    public void connect() {
        System.out.println("开始连接服务器");
        try {
            Socket clientSocket = new Socket("localhost", 6868);
            System.out.println("连接服务器成功");
            Thread.sleep(330);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            new Thread(new ClientReceiver(bufferedReader)).start();
            while (true) {
                out.println(br.readLine());
                out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
