package lk.oop.coursework;

import java.io.IOException;

//These methods will be overriding in the WestminsterShoppingManager class
public interface ShoppingManager {
    void addNewProduct();
    void deleteProduct();
    void printProducts();
    void saveProducts(String productFile) throws IOException;
    void loadProducts(String productFile) throws IOException, ClassNotFoundException;


}
