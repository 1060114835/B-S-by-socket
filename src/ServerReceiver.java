import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 负责接受来自客户端的数据，并将客户端发来的消息转发
 */

public class ServerReceiver implements Runnable {
    private final List<ClientBean> clients;
    private final ClientBean currentClient;

    public ServerReceiver(List<ClientBean> clients, ClientBean currentClient) {
        this.clients = clients;
        this.currentClient = currentClient;
    }


    @Override
    public void run() {
        while (true) {
            String data = "";
            try {
                clients.add(currentClient);
                data = currentClient.getIn().readLine();
                int id = Integer.parseInt(data.substring(data.length() - 1));
                System.out.println("ID为" + currentClient.getId() + "的主机" + "向" + "Id为" + id + "发送了一条消息");
                PrintWriter writer = clients.get(id - 1).getOut();
                writer.println(data);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("ID为" + currentClient.getId() + "发送的消息：" + data);
            }
        }

    }
}
