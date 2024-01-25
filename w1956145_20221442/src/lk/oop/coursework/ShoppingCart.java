package lk.oop.coursework;

import java.util.ArrayList;
import java.util.List;

// Represents the shopping cart
public class ShoppingCart {

    // store products in the cart
    private List<CartItem> cartItems;

    //Constructor
    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    // Add product to the cart
    public void addProduct(Product product) {
        // Check whether the product is in the cart
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductID().equals(product.getProductID())) {
                // Increment the quantity
                cartItem.incrementQuantity();
                return;
            }
        }

        // If the product is not in the cart, add a new cart item
        cartItems.add(new CartItem(product, 1));
    }

    //Remove Product from the cart
    public void removeProduct(Product product) {
        cartItems.remove(product);
    }

    //Getter
    public List<CartItem> getCartItems() {
        return cartItems;
    }


    // Calculate the total cost
    public double calculateTotal() {
        // Using Java streams
        return cartItems.stream()
                // Mapping each CartItem to its total cost (price * quantity)
                .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
                // Getting the total costs of all items in the cart
                .sum();
    }
    // Calculate discount for 3 or more items
    public double calculateTotalDiscount() {
        double totalDiscount = 0;
        // Iterate through each CartItem in the shopping cart
        for (CartItem item : cartItems) {
            // Accumulate the discount for each item with a quantity of 3 or more
            totalDiscount += item.getThreeItemDiscount();
        }
        return totalDiscount;
    }

    // Method to calculate the total cost after applying discounts
    public double calculateFinalTotal() {
        // Get the total cost before applying any discounts
        double totalCost = calculateTotal() ;
        // Get the total discount for items with a quantity of 3 or more
        double totalDiscount = calculateTotalDiscount();
        // Subtract the total discount from the total cost to get the final cost after discounts
        totalCost -= totalDiscount;
        return totalCost;
    }


    @Override
    public String toString() {
        return cartItems.toString();
    }

    //Inner class stores the product in the cart and its quantity
    public static class CartItem {
        private Product product;
        private int quantity;

        public  CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        //increment the quantity of the product in the cart
        public void incrementQuantity() {
            quantity++;
        }

        // Method to calculate the discount for items with a quantity of 3 or more
        public double getThreeItemDiscount() {

            if (quantity >= 3) {
                // Assuming the discount is 20% of the total price of these items
                double totalDiscount= (product.getPrice()*quantity) * 0.2;
                // Use the String.format() method to format the total discount to 2 decimal places
                return Double.valueOf(String.format("%.2f", totalDiscount));
            }
            return 0;
        }

        }
    }