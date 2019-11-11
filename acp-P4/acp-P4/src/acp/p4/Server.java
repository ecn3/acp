/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p4;

import java.io.*;
import java.net.*;

public class Server {
       public static void main(String[] args) throws IOException {
          ServerSocket ss = new ServerSocket(8081);
          
          Socket s1 = ss.accept(); // takes in a connection
          
          System.out.println("Connection made");
          // recieve messages from client 1
          InputStreamReader in1 = new InputStreamReader(s1.getInputStream());
          BufferedReader bf1 = new BufferedReader(in1);
          
          //get message
          String c1Message = bf1.readLine();
          //print mesage
          System.out.println("Client1 : "+ c1Message);
          
          //send message to client1
          PrintWriter pr1 = new PrintWriter(s1.getOutputStream());
          pr1.println("message to client");
          pr1.flush();
          
    }
}
