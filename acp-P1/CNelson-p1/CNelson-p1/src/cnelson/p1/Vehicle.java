/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnelson.p1;

/**
 *
 * @author cop4856
 */
public class Vehicle {

    //variables
    private String make;
    private String size;
    private double weight;
    private double engineSize;
    private boolean isImport;

    //defualt constructor created by java
    public Vehicle(String make, String size, double weight, double engineSize, boolean isImport) {
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

    public boolean isIsImport() {
        return isImport;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public void setIsImport(boolean isImport) {
        this.isImport = isImport;
    }

}
