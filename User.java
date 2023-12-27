public class User {
    private String username; // Name of the user - String used to represent characters and words
    private String password;
    private ShoppingCart cart; // User associated shopping cart - composed from cart class
    
    public User(String username, String password) {
      this.username = username;
      this.password = password;
      this.cart = new ShoppingCart(); // Composed from another class
    }
    
    public String getUsername() { // Fetch user-name - Getter
      return username;
    }
  
    public void setUsername(String username) {
      this.username = username;
    }
    
    public String getPassword() { // Fetch password - Getter
      return password;
    }
    
    public void setPassword(String password) {
      this.password = password;
  }

    public ShoppingCart getCart() { // Fetch Cart method - Getter
      return cart;
    }
}
