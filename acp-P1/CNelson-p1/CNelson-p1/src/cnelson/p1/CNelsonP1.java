package cnelson.p1;

import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author Christian Nelson
 */
public class CNelsonP1 {

    private static final String filename="Vehicles.dat";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        CNelsonP1 objectIO = new CNelsonP1();
              
        System.out.println("Project 1 Christian Nelson" + "\n");
        System.out.println("Creating Vehicles" + "\n");

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

