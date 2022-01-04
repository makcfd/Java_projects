import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{

        String host = "netology.homework";

        int port = 9090;

        try (
             Socket clientSocket = new Socket(host, port);

             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            System.out.println("================================");
            System.out.println("= This is client's side of log =");
            System.out.println("================================");
            // step 1: the client responds the name to the server
            String serverQuestion1 = in.readLine();
            System.out.println("Received a question from the server: " + serverQuestion1);
            out.println("DeathStar\n");


            // step 2: the client responds if it a child or not
            String serverQuestion2 = in.readLine();
            System.out.println("Receiver an additional question from the server: " + serverQuestion2);
            out.println("no");
            
            // step 3: final response
            String serverResponse = in.readLine();
            System.out.println("\nFinal server's response: " + serverResponse);
        }
    }
}
