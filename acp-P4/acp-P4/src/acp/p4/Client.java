/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p4;

/**
 *
 * @author Christian
 */
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.out;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public final class Client extends Application{
    @Override
    public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    
    stage.setTitle(username);   
    Scene scene = new Scene(root);
        
    stage.setScene(scene);
    stage.show();    
    }
    
    String username;
    PrintWriter pw;
    BufferedReader br;
    Socket client;
    

    
    public Client(String uname, String servername) throws Exception{
        username=uname; // sets user name
        client=new Socket(servername,80); // adds client to server
        br=new BufferedReader(new InputStreamReader(client.getInputStream())); // makes a read3er for cleitn messages
        pw = new PrintWriter(client.getOutputStream(),true); //writter for client messages
        pw.println(uname); // sends user name to server
        System.out.println("Client: "+username); // prints user name to console
        new MessageThread().start(); // starts the thread that will do all of our tictoeing
    }

    public static void main(String[] args){   
        String SetUserName=JOptionPane.showInputDialog(null, "Enter name: ","TicTacToe", JOptionPane.PLAIN_MESSAGE);
        String servername="localhost";
            Platform.runLater(new Runnable() {
        @Override
            public void run() {
        Stage stage = new Stage();
            try {
            final AcpP4 ttt = new AcpP4();
                ttt.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        });
        try{
            new Client(SetUserName,servername); //creates new client
        }catch(Exception e){
            System.out.println("main: " +e.getMessage());
        }
    }
    class MessageThread extends Thread {
        
        @Override
        public void run(){
            String move;
            try{
                while(true){
                    move=br.readLine(); // reads in move sent by player
                }
            }catch(Exception e){
                System.out.println("MessageThread: "+e.getMessage());
            }
        }
    }
    
}
