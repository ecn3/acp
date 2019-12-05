package project5;

import java.io.*;
import java.net.*;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Server {
    
    public ResultSet createInstruments(Statement stat) throws Exception
  {
         stat.execute("CREATE TABLE Instruments (instName CHAR(12),instNumber INTEGER,cost DOUBLE,descrip CHAR(20))");
         stat.execute("INSERT INTO Instruments VALUES ('guitar',1,100.0,'yamaha')");
         stat.execute("INSERT INTO Instruments VALUES ('guitar',2,500.0,'gibson')");
         stat.execute("INSERT INTO Instruments VALUES ('bass',3,250.0,'fender')");
         stat.execute("INSERT INTO Instruments VALUES ('keyboard',4,600.0,'roland')");
         stat.execute("INSERT INTO Instruments VALUES ('keyboard',5,500.0,'alesis')");
         ResultSet result = stat.executeQuery("SELECT * FROM Instruments");
         return result;
  }

  public ResultSet createLocations(Statement stat) throws Exception
  {
         stat.execute("CREATE TABLE Locations (locName CHAR(12),locNumber INTEGER,address CHAR(50))");
         stat.execute("INSERT INTO Locations VALUES ('PNS',1,'Pensacola Florida')");
         stat.execute("INSERT INTO Locations VALUES ('CLT',2,'Charlotte North Carolina')");
         stat.execute("INSERT INTO Locations VALUES ('DFW',3,'Dallas Fort Worth Texas')");
         ResultSet result = stat.executeQuery("SELECT * FROM Locations");
         return result;
  }

  public ResultSet createInventory(Statement stat) throws Exception
  {
         stat.execute("CREATE TABLE Inventory (iNumber INTEGER,lNumber INTEGER,quantity INTEGER)");
         stat.execute("INSERT INTO Inventory VALUES (1,1,15)");
         stat.execute("INSERT INTO Inventory VALUES (1,2,27)");
         stat.execute("INSERT INTO Inventory VALUES (1,3,20)");
         stat.execute("INSERT INTO Inventory VALUES (2,1,10)");
         stat.execute("INSERT INTO Inventory VALUES (2,2,10)");
         stat.execute("INSERT INTO Inventory VALUES (2,3,35)");
         stat.execute("INSERT INTO Inventory VALUES (3,1,45)");
         stat.execute("INSERT INTO Inventory VALUES (3,2,10)");
         stat.execute("INSERT INTO Inventory VALUES (3,3,17)");
         stat.execute("INSERT INTO Inventory VALUES (4,1,28)");
         stat.execute("INSERT INTO Inventory VALUES (4,2,10)");
         stat.execute("INSERT INTO Inventory VALUES (4,3,16)");        
         ResultSet result = stat.executeQuery("SELECT * FROM Inventory");
         return result;
  }
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
