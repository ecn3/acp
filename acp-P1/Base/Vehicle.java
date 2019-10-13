public class Vehicle {

    //variables
    private String make;
    private String size;
    private int weight;
    private double engineSize;
    private boolean isImport;

    //defualt constructor created by java
    public Vehicle(String make, String size, int weight, double engineSize, boolean isImport) {
        this.make = make;
        this.size = size;
        this.weight = weight;
        this.engineSize = engineSize;
        this.isImport = isImport;
    }

    public String getMake() {
        return make;
    }

    public String getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public boolean getIsImport() {
        return isImport;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public void setIsImport(boolean isImport) {
        this.isImport = isImport;
    }

}
