import java.util.ArrayList;
import java.util.List;

// Define a class to represent a shopping cart - inherits from Product class
public class ShoppingCart { 
  
  private List<Product> items; // Create list to store products in cart - All carts are composed of this list

  // Construct an empty cart - Aggregation relationship with product class
  public ShoppingCart() {
    items = new ArrayList<>();
  }
  
  // Method to add product to the cart
  public void addItem(Product product) { 
    items.add(product);
  }
  
  // Method to remove product from the cart
  public void removeItem(Product product) {
    items.remove(product);
  }
  
  // Method to calculate the total price of all products in the cart
  public double calculateTotalPrice() {
    double totalPrice = 0;
    for (Product product : items) {
      totalPrice += product.getPrice();
    }
    return totalPrice;
  }
}