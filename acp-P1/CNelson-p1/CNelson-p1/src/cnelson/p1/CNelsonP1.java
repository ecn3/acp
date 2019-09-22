package cnelson.p1;

import java.util.ArrayList;

/**
 * @author Christian Nelson
 */
public class CNelsonP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Project 1 Christian Nelson" + "\n");
        System.out.println("Creating Vehicle" + "\n");

        ArrayList<RandomVehicles> vehicles = new ArrayList<>(10); // arraylist of vehicles
        //RandomVehicles randVehicle = new RandomVehicles();
        // System.out.println("Vehicle " + randVehicle.getRandSize() +"\n");

        //create 10 instances of vehicle
        for (int i = 0; i < 10; i++) {
            RandomVehicles randVehicle = new RandomVehicles();
            vehicles.add(randVehicle);

            System.out.println("Vehicle " + (i + 1) + " "
                    + randVehicle.getRandMake() + " "
                    + randVehicle.getRandSize() + " "
                    + randVehicle.getRandEngineSize() + " "
                    + randVehicle.getRandWeight()+ " "
                    + randVehicle.getRandIsImport()
                    + "\n");
        }

    }
}
