package lk.oop.coursework;

public class Electronics extends Product {
    // Instance variables
    private String brand;
    private String warrantyPeriod;

    //Constructor
    public Electronics(String productID, String productName, int noOfAvailableItems, double price, String brand, String warrantyPeriod) {
        super(productID, productName, noOfAvailableItems, price,"Electronics");
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    //Getters and Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    // Override toString method
    @Override
    public String toString() {
        return "Electronics{" +
                "brand='" + brand + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                '}';
    }
}
