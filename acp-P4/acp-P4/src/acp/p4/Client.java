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
    
       public boolean myTurn = false;
        
       public static void main(String[] args) throws IOException {
          Socket s = new Socket("localhost", 8081);
          String clientMessage = "";
          
          //is connected
          Scanner scanner = new Scanner(System.in); // get user input
          
          PrintWriter pr = new PrintWriter(s.getOutputStream()); // get client output
          
          InputStreamReader in = new InputStreamReader(s.getInputStream()); // get server input
          BufferedReader bf = new BufferedReader(in); // return server input
          
          while(!(clientMessage.contains("q"))){
              
          //user to client
          System.out.print("Client: ");
          clientMessage = scanner.nextLine();
          
          //client to server
          pr.println(clientMessage +'\n');
          pr.flush();
          
          // server to client
          String serverMessage = bf.readLine();
          System.out.println(serverMessage);
          }
          }
}
