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
        //im needed but unused
    }
    
    public static String username;
    PrintWriter pw;
    BufferedReader br;
    Socket client;
    public static final Project5 ttt = new Project5();
    public static Stage stage = new Stage();
    

    
    public Client(String servername) throws Exception{
        client=new Socket(servername,80); // adds client to server
        br=new BufferedReader(new InputStreamReader(client.getInputStream())); // makes a read3er for cleitn messages
        pw = new PrintWriter(client.getOutputStream(),true); //writter for client messages
        new MessageThread().start(); // starts the thread that will do all of our tictoeing
    }

    public static void main(String[] args){   
        String servername="localhost";
            Platform.runLater(new Runnable() {
        @Override
            public void run() {

            try {
                ttt.start(stage);
            } catch (Exception ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        });
        try{
            new Client(servername); //creates new client
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
