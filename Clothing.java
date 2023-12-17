public class Clothing extends Product {
    private int size;
    private String colour;

    public Clothing(){
    }

    public Clothing(String productID, String productName, int noOfAvailableItems, int price, int size, String colour) {
        super(productID, productName, noOfAvailableItems, price);
        this.size=size;
        this.colour=colour;
    }
    
}
