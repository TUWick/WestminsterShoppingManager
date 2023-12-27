abstract public class Product{
    private String productID;
    private String productName;
    private int noOfAvailableItems;
    private double price;

    public Products(){
    }

    public String Product(String productID,String productName,int noOfAvailableItems,double price){
        this.productID=productID;
        this.productName=productName;
        this.noOfAvailableItems=noOfAvailableItems;
        this.price=price;

        public String getProductID(){
            return productID;
        }
        public void setProductID(productID){
            this.productID=productID;
        }
        public String getProductName(){
            return productName;
        }
        public void setProductName(productName){
            this.productName=productName;
        }
        public String getNoOfAvailableItems(){
            return noOfAvailableItems;
        }
        public void setNoOfAvailableItems(noOfAvailableItems){
            this.noOfAvailableItems=noOfAvailableItems;
        }
        public String getPrice(){
            return price;
        }
        public void setPrice(price){
            this.price=price;
        }
    }



}