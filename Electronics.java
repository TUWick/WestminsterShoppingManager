public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(){
    }


    public Electronics(String productID, String productName, int noOfAvailableItems, double price, String brand, int warrantyPeriod){
        super(productID, productName, noOfAvailableItems, price);
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;


        public String getBrand(){
            return brand;
        }
        public void setBrand(brand){
            this.brand=brand;
        }
        public String getWarrantyPeriod(){
            return warrantyPeriod;
        }
        public void setWarrantyPeriod(warrantyPeriod){
            this.warrantyPeriod=warrantyPeriod;
        }
    }
    
}
