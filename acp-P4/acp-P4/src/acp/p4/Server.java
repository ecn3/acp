/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p4;

import static acp.p4.Client.scanner;
import java.io.*;
import java.net.*;

public class Server {
    
        public static String client1Name = "Client 1: ";
        public static String client2Name = "Client 2: ";
        public static int clientTurn = 1;
    
        
       public static void main(String[] args) throws IOException {
          ServerSocket ss = new ServerSocket(8081);

          
          String clientMessage1 = "";
          String clientMessage2 = "";
          
          Socket s1 = ss.accept(); // takes in a connectio
          Socket s2 = ss.accept(); // takes in a connectio
          
          // recieve messages from client 1
          InputStreamReader in1 = new InputStreamReader(s1.getInputStream());
          BufferedReader bf1 = new BufferedReader(in1);
          
          // recieve messages from client 2
          InputStreamReader in2 = new InputStreamReader(s2.getInputStream());
          BufferedReader bf2 = new BufferedReader(in2);
          
          // send message to client 1
          PrintWriter pr1 = new PrintWriter(s1.getOutputStream());
          // send message to client 2
          PrintWriter pr2 = new PrintWriter(s2.getOutputStream());
          
          //start by getting client name
          clientMessage1 = bf1.readLine();
          setClient1Name(clientMessage1);
          
            //start by getting client name
          clientMessage2 = bf2.readLine();
          setClient2Name(clientMessage2);
          
          while(!(clientMessage1.contains("q"))){ 
            //read in client 1 message  
            clientMessage1 = bf1.readLine();
            
           //print mesage c1 to server
            System.out.println(client1Name+clientMessage1);
            
            //read in client 2 message  
            clientMessage2 = bf2.readLine();
            
            //print message c2 to server
            System.out.println(client2Name+ clientMessage2);  
            
            if(clientTurn == 1){
            //send message from c2 to client1
            pr1.println(client2Name+clientMessage2);
            pr1.flush();
            }
            if(clientTurn == 2){
            //send message from c1 to client2
            pr2.println(client1Name+clientMessage1);
            pr2.flush();   
            }
            if(clientTurn == 1){
             clientTurn = 2;   
            }else{
              clientTurn = 1;  
            }
           }
          
    }
    public static void setClient1Name(String name){
       client1Name = name+": ";
    }
    public static void setClient2Name(String name){
       client2Name = name+": ";
    }
     
}
