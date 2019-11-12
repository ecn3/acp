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
       public static void main(String[] args) throws IOException {
          Socket s = new Socket("localhost", 8081);
          String clientMessage = "";
          
          while(!(clientMessage.contains("q"))){
          //user to client
          Scanner scanner = new Scanner(System.in);
          System.out.print("Client: ");
          clientMessage = scanner.nextLine();
          
          //client to server
          PrintWriter pr = new PrintWriter(s.getOutputStream());
          pr.println(clientMessage +'\n');
          pr.flush();
          
          // server to client
          InputStreamReader in = new InputStreamReader(s.getInputStream());
          BufferedReader bf = new BufferedReader(in);
          
          String serverMessage = bf.readLine();
          
          System.out.println(serverMessage);
          }
    }
}
