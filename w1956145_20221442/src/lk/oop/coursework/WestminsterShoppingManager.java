package lk.oop.coursework;

import javax.swing.*;
import java.util.*;
import java.io.*;

public class WestminsterShoppingManager implements ShoppingManager{
    public static List <Product> productList = new ArrayList<>();
    private static WestminsterShoppingManager manager = new WestminsterShoppingManager();


    public void displayMenu() {
        Scanner USER_INPUT = new Scanner(System.in);

            System.out.println("\n------------------------------MENU------------------------------");
            System.out.println();
            System.out.println("" +
                    "1) Press 1 to Add a product \n" +
                    "2) Press 2 to Delete a product \n" +
                    "3) Press 3 to Print the list of available products\n" +
                    "4) Press 4 to Save products\n" +
                    "5) Press 5 to Open the Shopping Cart GUI\n"+
                    "6) Press 6 to Exit the program\n");
            for(int i = 0; i < 65; i++) {
                System.out.print("-");
            }
            System.out.println();
            System.out.print("Enter your option: ");
            int choice = getValidIntInput(USER_INPUT);

            switch (choice){
                case 1:
                    addNewProduct();
                    displayMenu();
                    break;
                case 2:
                    deleteProduct();
                    displayMenu();
                    break;
                case 3:
                    printProducts();
                    displayMenu();
                    break;
                case 4:
                    saveProducts("products.txt");
                    displayMenu();
                    break;
                case 5:
                    System.out.println(" ");
                    System.out.println("-----------------------Shopping Cart GUI-------------------------");
                    System.out.println(" ");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            // Instantiate and open the Swing GUI from another class
                            WestminsterShoppingCartGUI shoppingApplicationGUI = new WestminsterShoppingCartGUI();
                            WestminsterShoppingCartGUI.showGUI();
                        }
                    });displayMenu();
                    break;
                case 6:
                    System.out.println("Thank You for using the System. Have a Pleasant Day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Option.Please Try Again!");
                    displayMenu();
                    }
        }

    public void addNewProduct() {
        System.out.println();
        System.out.println("----------------------------Add Product--------------------------");
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.println("Select the category to add a product?\n1.Clothing      2.Electronics");
        System.out.print("Enter Your Choice (1 or 2): ");
        String option1 = input.next();
        if (!option1.equals("1") && !option1.equals("2")) {
            System.out.println("Invalid Option.\nPlease Try Again!");
            addNewProduct();
        }
        System.out.println();
        System.out.print("Enter Product ID : ");
        String prodID = input.next();
        for (int x = 0; x < productList.size(); x++) {
            if (productList.get(x).getProductID().equals(prodID)) {
                System.out.println("Product already Exist.\nPlease Try Again!");
                displayMenu();
                return;
            }
        }
        System.out.print("Enter Product Name : ");
        String proName = input.next();
        System.out.print("Enter Product Price : ");
        double proPrice = 0;
            try {
                proPrice = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price : ");
                input.nextLine();  // clear the invalid input
                proPrice = input.nextDouble();  // get the next input
            }
            System.out.print("Enter the number of Items : ");
            int noOfItems;
                try {
                    noOfItems= input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number : ");
                    input.nextLine();  // clear the invalid input
                    noOfItems = input.nextInt();  // get the next input
                }

                    if (productList.size() > 50) {
                        System.out.println("You cannot exceed more than 50. Cannot add the product");

                        displayMenu();
                    }

                    if (option1.equals("1")) {
                        System.out.print("Enter Product Size : ");
                        String size = input.next();
                        System.out.print("Enter Product Colour : ");
                        String colour = input.next();
                        Clothing clothing = new Clothing(prodID, proName, noOfItems, proPrice, size, colour);
                        productList.add(clothing);

                    }

                    if (option1.equals("2")) {
                        System.out.print("Enter Product Brand : ");
                        String productBrand = input.next();
                        System.out.print("Enter Warranty Period : ");
                        String warrantyPeriod = input.next();

                        Electronics electronics = new Electronics(prodID, proName, noOfItems, proPrice, productBrand, warrantyPeriod);
                        productList.add(electronics);

                    }
                    System.out.println();
                    System.out.println("Successfully added the product!");
                    displayMenu();
        }

    public void deleteProduct() {
        System.out.println();
        System.out.println("--------------------------Delete Product-------------------------");
        System.out.println();
    Scanner input = new Scanner(System.in);

    System.out.println("Enter Product ID : ");
    String deleteProduct = input.next();
    boolean productFound = false;
    for (int x = 0; x < productList.size(); x++) {
        if (productList.get(x).getProductID().equals(deleteProduct)) {
//            productFound = true;
            Product deletedProduct = productList.get(x);
            productList.remove(x);
            System.out.println("Successfully deleted the product!");
            System.out.println("Total products remaining : " + productList.size());
            // Display information about the deleted product
            System.out.println("\nDeleted product information: ");
            if (deletedProduct instanceof Clothing) {
                Clothing clothing = (Clothing) deletedProduct;
                System.out.println("\nProduct deleted was a Clothing Item.");
                System.out.println("Product ID : " + clothing.getProductID());
                System.out.println("Product name : " + clothing.getProductName());
                System.out.println("Number of available items : " + clothing.getNoOfAvailableItems());
                System.out.println("Price : " + clothing.getPrice());
                System.out.println("Clothing Size : " + clothing.getSize());
                System.out.println("Clothing Colour : " + clothing.getColour());
            } else if (deletedProduct instanceof Electronics) {
                Electronics electronics = (Electronics) deletedProduct;
                System.out.println("\nProduct deleted was an Electronics Item");
                System.out.println("Product ID : " + electronics.getProductID());
                System.out.println("Product name : " + electronics.getProductName());
                System.out.println("Number of available items : " + electronics.getNoOfAvailableItems());
                System.out.println("Price : " + electronics.getPrice());
                System.out.println("Product Brand : " + electronics.getBrand());
                System.out.println("Product Warranty Period : " + electronics.getWarrantyPeriod());
            }
            return;
        }
    }
    if (!productFound) {
        System.out.println("\nProduct Not Found!");
    }
}

public void printProducts() {
    System.out.println();
    System.out.println("-------------------------List of Products-------------------------");

    // Sort the product list by product ID
    Collections.sort(productList, new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getProductID().compareTo(p2.getProductID());
        }
    });

    //adding all the items to "arraylist" from savedProducts arrayList
    //Print Products
    ArrayList<Product>arraylist=new ArrayList<>(productList);

    for(int x=0;x<arraylist.size();x++){
        System.out.println(" ");
        System.out.println("Product type : " + arraylist.get(x).getCategory());
        System.out.println("Product ID : "+arraylist.get(x).getProductID());
        System.out.println("Product name : " + arraylist.get(x).getProductName());
        System.out.println("Number of available items : " + arraylist.get(x).getNoOfAvailableItems());
        System.out.println("Price : " + arraylist.get(x).getPrice());

        if(arraylist.get(x) instanceof Electronics){
            System.out.println("Product Brand : "+((Electronics) arraylist.get(x)).getBrand());
            System.out.println("Product Warranty Period : " +((Electronics) arraylist.get(x)).getWarrantyPeriod());

        }
        if(arraylist.get(x) instanceof Clothing){
            System.out.print("Clothing Size : "+((Clothing) arraylist.get(x)).getSize());
            System.out.print("\nClothing Colour : "+((Clothing) arraylist.get(x)).getColour());

        } System.out.println();
    }
}
    @Override
    public void saveProducts(String productFile) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(productFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Product product : manager.productList) {
                objectOutputStream.writeObject(product);
            }

            objectOutputStream.close();
            System.out.println("\nProgress saved successfully to file : "+productFile+"\n");

        } catch (IOException e) {
            System.out.println("An error occurred while saving progress!\n" + e);
        }
    }


    @Override
    public void loadProducts(String productFile) {
        manager.productList.clear();
        try {
            FileInputStream fileInputStream = new FileInputStream(productFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            while (fileInputStream.available() > 0) {
                productList.add((Product) objectInputStream.readObject());
            }

            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    private int getValidIntInput(Scanner USER_INPUT) {
        try {
            return USER_INPUT.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            USER_INPUT.next(); // clear the invalid input
            return getValidIntInput(USER_INPUT);
        }
    }
    public static void main(String[] args) {
        File temp = new File("Products.txt"); //To check if data exists from a previous run
        if (temp.exists()) {
            System.out.println("\nProgress restored!!!");
            manager.loadProducts("Products.txt");
        }
        System.out.println("\nWelcome to the Westminster Shopping Manager!");
        manager.displayMenu();
    }

}
