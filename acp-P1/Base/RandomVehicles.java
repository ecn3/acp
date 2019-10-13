import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author cop4856
 */
public class RandomVehicles implements Serializable {
        //default serialVersion id
    private static final long serialVersionUID = 1L;
    
    private String randMake;
    private String randSize;
    private int randWeight;
    private double randEngineSize;
    private boolean randIsImport;

    public RandomVehicles() {
        //set the randoms
        setRandMake();
        setRandSize();
        setRandEngineSize();
        
        Vehicle randVehicle = new Vehicle(getRandMake(), getRandSize(), (int) getRandWeight(), getRandEngineSize(), getRandIsImport());
    }

    public String getRandMake() {
        return randMake;
    }

    public String getRandSize() {
        return randSize;
    }

    public double getRandWeight() {
        return randWeight;
    }

    public double getRandEngineSize() {
        return randEngineSize;
    }

    public boolean getRandIsImport() {
        return randIsImport;
    }

    public void setRandMake() {
        int myRandMake = (int) ((Math.random() * ((5 - 1) + 1)) + 1); // generates a random number between 1 and 5

        if (myRandMake == 1) {
            this.randMake = "Chevy";
        }
        if (myRandMake == 2) {
            this.randMake = "Ford";
        }
        if (myRandMake == 3) {
            this.randMake = "Toyota";
        }
        if (myRandMake == 4) {
            this.randMake = "Nissan";
        }
        if (myRandMake == 5) {
            this.randMake = "Hyundai";
        }

        //we set the import status here based on what the make is 
        if (myRandMake > 2) {
            this.randIsImport = true;
        } else {
            this.randIsImport = false;
        }
    }

    public void setRandSize() {
        int myRandSize = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
        // we set our weight based on the size
        Random r = new Random();
        if (myRandSize == 1) {
            this.randSize = "compact";
            this.randWeight = r.nextInt(2000-1500) + 1500;
        }
        if (myRandSize == 2) {
            this.randSize = "intermediate";
            this.randWeight = r.nextInt(2500-2000) + 2000;
        }
        if (myRandSize == 3) {
            this.randSize = "fullSized";
            this.randWeight = r.nextInt(4000-2500) + 2500;
        }
    }

    public void setRandEngineSize() {
        this.randEngineSize = (int) ((Math.random() * ((18.0 - 1.0) + 1.0)) + 1.0);
    }
}
