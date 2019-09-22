package cnelson.p1;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Christian Nelson
 */
public class CNelsonP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        System.out.println("Project 1 Christian Nelson" + "\n");
        System.out.println("Creating Vehicle" + "\n");

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

        if (args.length == 0) {
            System.out.println(
                    "Usage: java -classpath driver_class_path"
                    + File.pathSeparator
                    + ". TestDB database.properties");
            return;
        } else {
            System.out.println("args[0] = " + args[0]);
            SimpleDataSource.init(args[0]);
        }

        Connection conn = SimpleDataSource.getConnection();
        Statement stat = conn.createStatement();
        try {
            stat.execute("DROP TABLE Vehicles");
        } catch (Exception e) {
            System.out.println("drop failed");
        }

        try {
            //System.out.println("Vehicles " + vehicles.get(1).getRandMake());
            stat.execute("CREATE TABLE Vehicles (Make CHAR(20), Size CHAR(20), Weight INTEGER, EngineSize DOUBLE, Import BOOLEAN)");

            for (int i = 0; i < 10; i++) {
                stat.execute("INSERT INTO TABLE VALUES ("
                        + vehicles.get(i).getRandMake() + ","
                        + vehicles.get(i).getRandSize() + ","
                        + vehicles.get(i).getRandWeight() + ","
                        + vehicles.get(i).getRandEngineSize() + ","
                        + vehicles.get(i).getRandIsImport() + ","
                        + ")");
            }

            ResultSet result = stat.executeQuery("SELECT * FROM Vehicles");

            System.out.println("after inserts");
            ResultSetMetaData rsm = result.getMetaData();
            int cols = rsm.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(result.getString(i) + " ");
                }
                System.out.println("");
            }
            try {
                stat.execute("DROP TABLE Vehicles");
            } catch (Exception e) {
                System.out.println("drop failed");
            }
        } finally {
            conn.close();
            System.out.println("dropped Table Vehicles, closed connection and ending program");
        }

    }
}
