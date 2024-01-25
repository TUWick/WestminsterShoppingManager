package lk.oop.coursework;

import java.io.Serializable;

//super class implementing Serializable
abstract public class Product implements Serializable {
    // Instance variables
    private String productID;
    private String productName;
    private int noOfAvailableItems;
    private double price;
    private  String category;

    //Constructor
    public Product(String productID, String productName, int noOfAvailableItems, double price, String category) {
        this.productID = productID;
        this.productName = productName;
        this.noOfAvailableItems = noOfAvailableItems;
        this.price = price;
        this.category=category;
    }

    //Getters and Setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    public void setNoOfAvailableItems(int noOfAvailableItems) {
        this.noOfAvailableItems = noOfAvailableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getCategory() {return category;}

    // Override toString method
    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", noOfAvailableItems=" + noOfAvailableItems +
                ", price=" + price +
                '}';
    }
}
