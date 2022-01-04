import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server starts...");
        // define port which server will be listening to
        int port = 9090;

        // all servers work in infinite cycle
        // so that clients can connect at any time
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);

            // wait for a connection
            //  if a client connects we create clientSocket
            Socket clientSocket = serverSocket.accept();

            //  we use .getOutputStream() to write to that stream as our answer to the client request
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // we use getInputStream() to get data from client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // print to console port which has been given to the client by server after connection
            System.out.println("New connection accepted. Port: " + clientSocket.getPort());

            // name is data that server reads from client
            final String name = in.readLine();

            // write into output to client, basicall it is out answer
            out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

            // once a client request is finished and we do not expect further requests will be received from that client
            // we close the socket to release limited resources
            serverSocket.close();
        }
    }
}
