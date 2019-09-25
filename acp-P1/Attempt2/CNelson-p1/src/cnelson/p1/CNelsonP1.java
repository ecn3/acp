package cnelson.p1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class CNelsonP1 {
        private static final String filename="Vehicles.dat";

    public static void main(String[] args) throws Exception {
        
        CNelsonP1 objectIO = new CNelsonP1();
        
        ArrayList<RandomVehicles> vehicles = new ArrayList<>(10); // arraylist of vehicles
        //create 10 instances of vehicle
        for (int i = 0; i < 10; i++) {
            RandomVehicles randVehicle = new RandomVehicles();
            vehicles.add(randVehicle);
            
            System.out.println("Vehicle " + (i + 1) + " "
                    + randVehicle.getRandMake() + " "
                    + randVehicle.getRandSize() + " "
                    + randVehicle.getRandEngineSize() + " "
                    + randVehicle.getRandWeight() + " "
                    + randVehicle.getRandIsImport()
                    + "\n");
             //objectIO.WriteObjectToFile(vehicles.get(i)); // write to .dat
        }
         objectIO.WriteObjectToFile(vehicles); // write to .dat
         
        if (args.length == 0) //if we didnt give it a commandline param database.properties
        {
            System.out.println(
                    "Usage: java -classpath driver_class_path" //derby.jar
                    + File.pathSeparator
                    + ". TestDB database.properties");
            return;
        } else {
            System.out.println("args[0] = " + args[0]);
            SimpleDataSource.init(args[0]);
        }
        //1. createConnection()
        Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();
        try {
            stat.execute("DROP TABLE Test2");
        } catch (Exception e) {
            System.out.println("drop failed");
        }

        try {
            //2. createTable() //Creates Vehicle Table
            stat.execute("CREATE TABLE Vehicles (Make CHAR(20), Size CHAR(20), Weight INTEGER, EngineSize DOUBLE, Import BOOLEAN)");
            //3. addDataToTable()
            for (int i = 0; i < 10; i++) {
                stat.execute("INSERT INTO TABLE VALUES ("
                        + vehicles.get(i).getRandMake() + ","
                        + vehicles.get(i).getRandSize() + ","
                        + vehicles.get(i).getRandWeight() + ","
                        + vehicles.get(i).getRandEngineSize() + ","
                        + vehicles.get(i).getRandIsImport() + ","
                        + ")");
            }
            

            //4. issueQuery()
            ResultSet result = stat.executeQuery("SELECT * FROM Test2");

            System.out.println("after inserts");
            //5. Process results

            ResultSetMetaData rsm = result.getMetaData();
            int cols = rsm.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(result.getString(i) + " ");
                }
                System.out.println("");
            }
            try {
                stat.execute("DROP TABLE Test2");
            } catch (Exception e) {
                System.out.println("drop failed");
            }
        } finally {
            conn.close();
            System.out.println("dropped Table Test2, closed connection and ending program");
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
