/*
 implements https://www.youtube.com/watch?v=5rROgYJKj8c
 */
package acp.p4;

import java.io.*;
import java.net.*;
import static java.lang.System.out;
import java.util.Vector;

public class Server {
      public static void main(String[] args) throws Exception {
          new Server().createserver();
    }
    
      Vector<String> users=new Vector<String>();
      Vector<Manageuser> clients=new Vector<Manageuser>();

    private void createserver()throws Exception {
        ServerSocket server=new ServerSocket(80,10);
        out.println("Server is running");
        while(true){
            Socket client=server.accept();
            Manageuser c = new Manageuser(client);
            clients.add(c);
        }
    }
    public void sendToAll(String user, String message){
        for(Manageuser c : clients){
            if(!c.getchatuser().equals(user)){
                c.sendMessage(user,message);
            }
        }
    }
    
    class Manageuser extends Thread{
        String gotuser="";
        BufferedReader input;
        PrintWriter output;

        private Manageuser(Socket client) throws Exception {
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(client.getOutputStream(),true);
            gotuser = input.readLine();
            users.add(gotuser);
            start();
        }

        private void sendMessage(String chatuser, String chatmsg) {
            output.println(chatuser+" Says:"+chatmsg);
            
        }

        private String getchatuser() {
            return gotuser;
        }
        @Override
        public void run(){
            String line;
            try{
                while(true){
                    line=input.readLine();
                    if(line.equals("end")){
                        clients.remove(this);
                        users.remove(gotuser);
                        break;
                    }
                    sendToAll(gotuser,line);
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
                    
        }
    }
    
}
