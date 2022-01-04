import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{

        // define where to connect
        String host = "127.0.0.1";
        // port which server listen to
        int port = 9090;

        try (/**
              * create a client's socket  which:
              * - client app can use to read servers' responses
              * - client app can use to write requests
              * - can be closed
              */
             Socket clientSocket = new Socket(host, port);

             // send request to server
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

             // getting response from server
             // use InputStreamReader() to get text
             // wrapping in Buffer to be able to navigate in all recieved data
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            // we send to server data, in our case we send a name variable
            out.println("DeathStar\n");

            String response = in.readLine();
            System.out.println(response);
        }
    }
}
