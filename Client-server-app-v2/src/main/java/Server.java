import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server starts...");

        int port = 9090;

        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);

            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("================================");
            System.out.println("= This is server's side of log =");
            System.out.println("================================\n");
            System.out.println("New connection accepted. Port: " + clientSocket.getPort());

            // step 1: server requests name of a client
            out.println("Write your name");
            final String name = in.readLine();
            System.out.println("Name received: " + name + "\n");


            // step 2: server asks if the client a child
            System.out.println("Request info if the client a child");
            out.println("Are you child? (yes/no)");
            final String ifChild = in.readLine();

            // step 3a: final response
            if (ifChild.equals("no")) {
                System.out.println("The client is not a child...");
                out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name)); }
            // step 3b:
            else {
                System.out.println("The client is a child...");
                out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
            }

            serverSocket.close();
        }
    }
}
