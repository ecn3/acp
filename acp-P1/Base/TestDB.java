import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
   Tests a database installation by creating and querying
   a sample table. Call this program as
   java -classpath driver_class_path;. TestDB database.properties
*/
public class TestDB 
{
private static final String filename="Vehicles.dat";
   public static void main(String[] args) throws Exception
   {   
     TestDB objectIO = new TestDB();
   
      
        ArrayList<RandomVehicles> vehicles = new ArrayList<>(10); // arraylist of vehicles
        //create 10 instances of vehicle
        for (int i = 0; i < 10; i++) {
            RandomVehicles randVehicle = new RandomVehicles();
            vehicles.add(randVehicle);
            /*
            System.out.println("Vehicle " + (i + 1) + " "
                    + randVehicle.getRandMake() + " "
                    + randVehicle.getRandSize() + " "
                    + randVehicle.getRandEngineSize() + " "
                    + randVehicle.getRandWeight() + " "
                    + randVehicle.getRandIsImport()
                    + "\n");
            */
        }
         objectIO.WriteObjectToFile(vehicles); // write to .dat
   
      if (args.length == 0)
      {   
         System.out.println(
               "Usage: java -classpath driver_class_path" 
               + File.pathSeparator 
               + ". TestDB database.properties");
         return;
      }
      else 
		{
		   System.out.println("args[0] = " + args[0]);
         SimpleDataSource.init(args[0]);
		}
             //1. createConnection()
      Connection conn = SimpleDataSource.getConnection();
      Statement stat = conn.createStatement();     
 	   try {  
		  stat.execute("DROP TABLE Vehicles"); 
      }
	   catch (Exception e)
		{ System.out.println("drop failed"); }      

      try
      {
    //2. createTable() //Creates Vehicle Table
         stat.execute("CREATE TABLE Vehicles (Make CHAR(10), Size CHAR(15), Weight INTEGER, EngineSize DOUBLE, isImport BOOLEAN)");
         
          //3. addDataToTable()
          /*
            for (int i = 0; i < 10; i++) {
                stat.execute("INSERT INTO TABLE VALUES ("
                        + vehicles.get(i).getRandMake() + ","
                        + vehicles.get(i).getRandSize() + ","
                        + vehicles.get(i).getRandWeight() + ","
                        + vehicles.get(i).getRandEngineSize() + ","
                        + vehicles.get(i).getRandIsImport() + ","
                        + ")");
            }
            */
            stat.execute("INSERT INTO Vehicles VALUES ('Chevy','Compact', 1500, 1.0, false)");
            stat.execute("INSERT INTO Vehicles VALUES ('Toyota','Compact', 3000, 1.0, true)");
            stat.execute("INSERT INTO Vehicles VALUES ('Honda','Intermediate', 1500, 2.0, true)");
             //4. issueQuery()
                 //4. issueQuery()
            //all the vehicles that have been stored in the database.
            ResultSet result = stat.executeQuery("SELECT * FROM Vehicles");
            //all Chevys and Toyotas
            ResultSet result2 = stat.executeQuery("SELECT * FROM Vehicles where Make='Chevy' OR Make='Toyota'");
            //all vehicles weighing more than 2500 pounds
            ResultSet result3 = stat.executeQuery("SELECT * FROM Vehicles where Weight > 2500");
			  
			System.out.println("after inserts");
			ResultSetMetaData rsm = result.getMetaData();
			int cols = rsm.getColumnCount();
			  while(result.next())
			  {
			    for(int i = 1; i <= cols; i++)
               System.out.print(result.getString(i)+" ");
             System.out.println("");      
			  }
			try {  
		     stat.execute("DROP TABLE Vehicles"); 
         }
			catch (Exception e)
			{ System.out.println("drop failed"); }    
		}
      finally
      {
         conn.close();
			System.out.println("dropped Table Vehicles, closed connection and ending program");  
      }
   }
     public void WriteObjectToFile(Object serObj) {
 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file"+"\n");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
