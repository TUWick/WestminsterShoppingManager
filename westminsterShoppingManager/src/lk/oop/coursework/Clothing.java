package lk.oop.coursework;

public class Clothing extends Product {

    // Instance variables
    private String size;
    private String colour;

//Constructor
    public Clothing(String productID, String productName, int noOfAvailableItems, double price, String size, String colour) {
        super(productID, productName, noOfAvailableItems, price,"Clothing");
        this.size = size;
        this.colour = colour;
    }

    //Getters and Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Clothing{" +
                "size='" + size + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
