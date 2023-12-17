public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, int noOfAvailableItems, int price, private String brand, private int warrantyPeriod){
        super(productID, productName, noOfAvailableItems, price);
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;

    }
    
}
