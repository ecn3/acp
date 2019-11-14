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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class Client extends JFrame implements ActionListener{
    String username;
    PrintWriter pw;
    BufferedReader br;
    JTextArea chatmsg;
    JTextField chatip;
    JButton send,exit;
    Socket chatusers;
    
    public Client(String uname, String servername) throws Exception{
        super(uname);
        this.username=uname;
        chatusers=new Socket(servername,80);
        br=new BufferedReader(new InputStreamReader(chatusers.getInputStream()));
        pw = new PrintWriter(chatusers.getOutputStream(),true);
        pw.println(uname);
        buildInterface();
        new MessageThread().start();
    }

    private void buildInterface() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new FlowLayout());
        myPanel.add(new JLabel("Tic Tac Toe: "+username));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==exit){
            pw.println("end");
            System.exit(0);
        }else{
           pw.println(chatip.getText());
           chatip.setText(null);
        }
    }
    public static void main(String[] args){
        String SetUserName=JOptionPane.showInputDialog(null, "Enter name: ","Test App", JOptionPane.PLAIN_MESSAGE);
        String servername="localhost";
        try{
            new Client(SetUserName,servername);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    class MessageThread extends Thread {
        
        @Override
        public void run(){
            String line;
            try{
                while(true){
                    line=br.readLine();
                    chatmsg.append(line+"\n");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
}
