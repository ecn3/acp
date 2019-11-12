/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    
       public static boolean myTurn = true;
       public static String clientMessage = "";
       public  static Scanner scanner = new Scanner(System.in); // get user input 
       public String clientName = "";
       
       public static void main(String[] args) throws IOException {
          Client myClient = new Client();
          }

    public Client() throws IOException {
        Socket s = new Socket("localhost", 8081);
        PrintWriter pr = new PrintWriter(s.getOutputStream()); // get client output         
        InputStreamReader in = new InputStreamReader(s.getInputStream()); // get server input
        BufferedReader bf = new BufferedReader(in); // return server input
        
        // once connected get the name
        setName();
        run(pr, in, bf); // start running
    }
    
    public void run(PrintWriter pr, InputStreamReader in, BufferedReader bf) throws IOException{
           
          // send the name to the server
          pr.println(clientName);
          pr.flush(); 
          clientName+=": ";
          while(!(clientMessage.contains("q"))){
          
          while(myTurn){ 
          //user to client
          System.out.print(clientName);
          clientMessage = scanner.nextLine();
          
          //client to server
          pr.println(clientMessage +'\n');
          pr.flush();
          
          if(clientMessage.contains("your move")){
          myTurn = false;    
          }
          }
          // server to client
          String serverMessage = bf.readLine();
          System.out.println(serverMessage);
          myTurn = true;
          }
    }
    
    public void setName(){
       System.out.print("Enter your name: ");
       clientName = scanner.nextLine();// set the name
    }
       
}
