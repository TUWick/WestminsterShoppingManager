public class Clothing extends Product {
    private String size;
    private String colour;

    public Clothing(){
    }

    public Clothing(String productID, String productName, int noOfAvailableItems, double price, String size, String colour) {
        super(productID, productName, noOfAvailableItems, price);
        this.size=size;
        this.colour=colour;

        public String getSize(){
            return size;
        }
        public void setSize(String size){
            this.size=size;
        }
        public String getColour(){
            return colour;
        }
        public void setColour(String colour){
            this.colour=colour;
        }
    }
    
}
