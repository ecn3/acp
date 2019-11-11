/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p4;

import java.io.*;
import java.net.*;

public class Client {
       public static void main(String[] args) throws IOException {
          Socket s = new Socket("localhost", 8081);
          
          //client to server
          PrintWriter pr = new PrintWriter(s.getOutputStream());
          pr.println("Client connected");
          pr.flush();
          
          // server to client
          InputStreamReader in = new InputStreamReader(s.getInputStream());
          BufferedReader bf = new BufferedReader(in);
          
          String serverMessage = bf.readLine();
          
          System.out.println("Server : "+ serverMessage);
          
    }
}
