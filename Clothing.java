public class Clothing extends Product {
    private int size;
    private String colour;

    public Clothing(){
    }

    public Clothing(String productID, String productName, int noOfAvailableItems, int price, int size, String colour) {
        super(productID, productName, noOfAvailableItems, price);
        this.size=size;
        this.colour=colour;

        public int getSize(){
            return size;
        }
        public void setSize(size){
            this.size=size;
        }
        public String getColour(){
            return colour;
        }
        public void setColour(colour){
            this.colour=colour;
        }
    }
    
}
