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
          
          String clientMessage1 = "";
          String clientMessage2 = "";
          
          Socket s1 = ss.accept(); // takes in a connectio
          Socket s2 = ss.accept(); // takes in a connectio
          // recieve messages from client 1
          InputStreamReader in1 = new InputStreamReader(s1.getInputStream());
          BufferedReader bf1 = new BufferedReader(in1);
            // recieve messages from client 1
          InputStreamReader in2 = new InputStreamReader(s2.getInputStream());
          BufferedReader bf2 = new BufferedReader(in2);
          
          while(!(clientMessage1.contains("q"))){ 
          //get message
          clientMessage1 = bf1.readLine();
          //get message
          clientMessage2 = bf2.readLine();
          //print mesage c1
          System.out.println("Client1 : "+ clientMessage1);
          //print mesage c2
          System.out.println("Client2 : "+ clientMessage2);
          
          //send message to client1
          PrintWriter pr1 = new PrintWriter(s1.getOutputStream());
          pr1.println("Client 2: "+clientMessage2);
          pr1.flush();
           //send message to client1
          PrintWriter pr2 = new PrintWriter(s2.getOutputStream());
          pr2.println("Client 1: "+clientMessage1);
          pr2.flush();
           }
          
    }
}
