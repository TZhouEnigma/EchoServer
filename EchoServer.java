import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.*;



/*
 * An echo server that simply echoes messages back.
 */
public class EchoServer {
    
    // Create a socket for the server 
    private static ServerSocket serverSocket = null;
    // Create a socket for the user 
    private static Socket userSocket = null;
    private static BufferedReader input_stream = null;
    private static PrintStream output_stream = null;



    public static void main(String args[]) {
        
        // The default port number.
        int portNumber = 8000;
        if (args.length < 1) {
            System.out.println("Usage: java Server <portNumber>\n"
                                   + "Now using port number=" + portNumber + "\n");
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }
        
        
        /*
         * Open a server socket on the portNumber (default 8000). 
         */
 // YOUR CODE;
        try{
         serverSocket = new ServerSocket(portNumber);
         serverSocket.setSoTimeout(10000);
        }
        catch(IOException e){
        System.out.println(e);
        }
        /*
         * Create a user socket for accepted connection 
         */
        
        while(true) {
         try {
            System.out.println("Waiting for user on port " + 
            serverSocket.getLocalPort() + "...");
            userSocket = serverSocket.accept();
            System.out.println("Just connected to1 " + userSocket.getRemoteSocketAddress());
            System.out.println("Just connected to2 " + userSocket.getLocalSocketAddress());
             /*
              * Close the output stream, close the input stream, close the socket.
              */
           
            input_stream=new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            output_stream=new PrintStream(userSocket.getOutputStream());
            
            // Read input from user String inputLine;
            String inputLine;
            while ((inputLine = input_stream.readLine()) != null) 
            {
              System.out.println("Received input: " + inputLine);
              // Return input to user as output 
              output_stream.println("the server says "+inputLine);
            }           
             input_stream.close();
             output_stream.close();
             userSocket.close(); 
                }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}





