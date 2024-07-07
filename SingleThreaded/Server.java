
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 * Server
 */
public class Server {

    public void run() throws IOException, UnknownHostException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(70000);
        while (true) {

            System.out.println("Server is listening on port : " + port);
            Socket acceptConnection = socket.accept();
            System.out.println("Connection accepted from client " + acceptConnection.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(acceptConnection.getOutputStream());
            BufferedReader fromClient = new BufferedReader(
                    new InputStreamReader(acceptConnection.getInputStream()));
            toClient.println("Hello from the server");
            toClient.close();
            fromClient.close();
            acceptConnection.close();

        }

    }

    public static void main(String[] args) {

        try {
            Server server = new Server();
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}