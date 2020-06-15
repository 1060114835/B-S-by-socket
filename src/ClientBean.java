import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * 定义了一个客户端连接的实体类
 */
public class ClientBean {

    public ClientBean(Socket socket, int id) {
        this.clientSocket = socket;
        this.id = id;
        try {
            this.in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //描述客户端的当前id
    private final int id;
    // 客户端的连接
    private final Socket clientSocket;
    //输入流
    private BufferedReader in;
    //输出流
    private PrintWriter out;

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public int getId() {
        return id;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    @Override
    public String toString() {
        return "ClientBean{" +
                "id=" + id +
                ", clientSocket=" + clientSocket +
                ", in=" + in +
                ", out=" + out +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientBean that = (ClientBean) o;
        return id == that.id &&
                Objects.equals(clientSocket, that.clientSocket) &&
                Objects.equals(in, that.in) &&
                Objects.equals(out, that.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientSocket, in, out);
    }
}
