package cnelson.p1;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class CNelsonP1 {

    public static void main(String[] args) throws Exception {
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
            stat.execute("INSERT INTO Test2 VALUES ('Romeo',27, true)");
            stat.execute("INSERT INTO Test2 VALUES ('Juliet',25, true)");
            stat.execute("INSERT INTO Test2 VALUES ('Tom',64, true)");
            stat.execute("INSERT INTO Test2 VALUES ('Dick',55, false)");
            stat.execute("INSERT INTO Test2 VALUES ('Harry',33, true)");

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
}
