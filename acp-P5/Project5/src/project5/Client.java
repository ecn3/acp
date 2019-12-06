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

// Client2 class that 
// sends data and receives also 
  
import java.io.*; 
import java.net.*; 
import java.sql.ResultSet;
import java.sql.Statement;

public class Client {
    public static void main(String args[]) 
        throws Exception 
    { 
  
        // Create client socket 
        Socket s = new Socket("localhost", 8081); 
  
        // to send data to the server 
        DataOutputStream dos 
            = new DataOutputStream( 
                s.getOutputStream()); 
  
        // to read data coming from the server 
        BufferedReader br 
            = new BufferedReader( 
                new InputStreamReader( 
                    s.getInputStream())); 
  
        // to read data from the keyboard 
        BufferedReader kb 
            = new BufferedReader( 
                new InputStreamReader(System.in)); 
        String str, str1; 
  
        // repeat as long as exit 
        // is not typed at client 
        while (!(str = kb.readLine()).equals("exit")) { 
  
            // send to the server 
            dos.writeBytes(str + "\n"); 
  
            // receive from the server 
            str1 = br.readLine(); 
  
            System.out.println(str1); 
        } 
  
        // close connection. 
        dos.close(); 
        br.close(); 
        kb.close(); 
        s.close(); 
    } 
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
}
