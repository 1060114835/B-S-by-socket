import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver implements Runnable {

    private BufferedReader reader;

    public ClientReceiver(BufferedReader reader) {
        this.reader = reader;
    }

    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                if (!msg.startsWith("Server"))
                    System.out.println("当前客户端接收到消息：" + msg.substring(0, msg.length() - 1));
                else
                    System.out.println("收到消息：" + msg);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
