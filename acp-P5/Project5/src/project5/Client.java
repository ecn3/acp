/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5;

/**
 *
 * @author Christian
 */
// Client2 class that 
// sends data and receives also 
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.stage.Stage;

public class Client {

    public static Socket socket; // client socket 
    public static BufferedReader serverBufferedReader; //read data coming from the server
    public static BufferedReader keyboardBufferedReader; //read data from the keyboard 
    public static DataOutputStream toServerdos; // send data to the server 
    public static String sendToServerStr, recieveFromServerStr;
    
    public Client() throws Exception{

        socket = new Socket("localhost", 8081);
        toServerdos = new DataOutputStream(socket.getOutputStream());
        serverBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        keyboardBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //buildInterface();
        new MessageThread().start();
    }

    public static void main(String args[]) throws Exception {
        // create client
      try{
            new Client();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        // repeat as long as exit 
        // is not typed at client 
        while (!(sendToServerStr = keyboardBufferedReader.readLine()).equals("exit")) {
            // send to the server 
            toServerdos.writeBytes(sendToServerStr + "\n");
            // receive from the server 
            recieveFromServerStr = serverBufferedReader.readLine();

            System.out.println(recieveFromServerStr);
        }
        // close connection. 
        toServerdos.close();
        serverBufferedReader.close();
        keyboardBufferedReader.close();
        socket.close();
    }
    
        class MessageThread extends Thread {
        
        @Override
        public void run(){
            String line;
            try{
                while(true){
                    line=serverBufferedReader.readLine();
                    //edit second window text field
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
}
