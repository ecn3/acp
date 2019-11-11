
package acp.p4;

/**
 *
 * @author Christian
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
      public static void main(String args[]) {
       // String host = "127.0.0.1";
        int port = 8081;
        //new Client(host, port);
        new Client("localhost", port);

    }

    public  Client(String host, int port) {
        try {
            System.out.println("Connecting to host " + host + " on port " + port + ".");
            System.out.println("Enter message, q to quit");
            Socket echoSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                echoSocket = new Socket(host,port);
                out = new PrintWriter(echoSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            /** {@link UnknownHost} object used to read from console */
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("client: ");
            String userInput = stdIn.readLine();
            while (!userInput.equals("q")) {
                out.println(userInput);
                System.out.println("server: " + in.readLine());
                System.out.print("client: ");
                userInput = stdIn.readLine();
            }
            /** Closing all the resources */
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
