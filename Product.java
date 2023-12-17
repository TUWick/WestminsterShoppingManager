abstract public class Product {
    private String productID=null;
    private String productName=null;
    private int noOfAvailableItems;
    private int price;

    public Product(String productID,String productName,int noOfAvailableItems,int price){
        this.productID=productID;
        this.productName=productName;
        this.noOfAvailableItems=noOfAvailableItems;
        this.price=price;
    }



}