package lk.oop.coursework;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WestminsterShoppingCartGUI {
    private static DefaultTableModel tableModel;
    private static DefaultTableModel shoppingCartTableModel;
    private static JFrame frame;
    private static JLabel selectedProductLabel;
    private static JTextArea productDetailsTextArea;

    private static JTable shoppingCartTable;

    private static ShoppingCart shoppingCart;
    private static JTextArea costTextArea;


    public static void showGUI() {
        shoppingCart = new ShoppingCart();
        // Create the main window
        JFrame frame = new JFrame("Westminster Shopping Centre");

        // Set the close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a JPanel for the combo box and shopping cart button
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create a JLabel (text label) to the panel
        JLabel label = new JLabel("\nSelect Product Category:\n");

        // Create a JComboBox for types of the products
        String[] productTypes = {"All", "Electronics", "Clothing"};
        JComboBox<String> comboBox = new JComboBox<>(productTypes);

        // Add an ActionListener to respond to selection changes
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions based on the selected item
                String selectedProductType = (String) comboBox.getSelectedItem();
            }
        });

        // Create a "Shopping Cart" button
        JButton shoppingCartButton = new JButton("Shopping Cart");

        // Add ActionListener to the "Shopping Cart" button
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new window (you can replace this with your shopping cart logic)
                openShoppingCartWindow();
            }
        });

        // Add components to the comboPanel
        comboPanel.add(label);
        comboPanel.add(comboBox);
        comboPanel.add(shoppingCartButton);

        // Add the comboPanel to the main panel
        panel.add(comboPanel);

        // Set padding between the comboPanel and the table
        panel.add(Box.createRigidArea(new Dimension(0, 12)));

        // Create a table model with 5 columns
        tableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Category", "Price($)", "Info"}, 0);


        for (Product product : WestminsterShoppingManager.productList) {
            if (product instanceof Electronics) {
                Object[] row = {product.getProductID(), product.getProductName(),
                        product.getCategory(), product.getPrice(), (((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod())};
                tableModel.addRow(row);
            } else if (product instanceof Clothing) {
                Object[] row = {product.getProductID(), product.getProductName(),
                        product.getCategory(), product.getPrice(), ( ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour())};
                tableModel.addRow(row);
            }
        }

        // Create a JTable with the table model
        JTable table = new JTable(tableModel);

        table.setPreferredScrollableViewportSize(new Dimension(450, 130));

        // Add a ListSelectionListener to detect row selection changes
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Handle row selection
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Display the selected product details below the table
                    displaySelectedProductDetails(selectedRow);
                }
            }
        });

        // Add the table to a JScrollPane for scrolling if needed
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scrollPane to the main panel
        panel.add(scrollPane);

        // Set padding between the table and the product details section
        panel.add(Box.createRigidArea(new Dimension(0, 12)));

        // Create a JLabel for the product details title
        JLabel productDetailsTitleLabel = new JLabel("Product Details");

        // Add the product details title to the main panel
        panel.add(productDetailsTitleLabel);

        // Add a JTextArea for displaying product details
        productDetailsTextArea = new JTextArea();
        productDetailsTextArea.setEditable(false);

        // Set preferred size for the product details text area
        productDetailsTextArea.setPreferredSize(new Dimension(450, 130));

        // Add the product details text area to the main panel
        panel.add(productDetailsTextArea);


        JButton addToCartButton = new JButton("Add to Cart");

        // Add ActionListener to the "Add to Cart" button
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row from the main table
                int selectedRow = table.getSelectedRow();

                // Check if any row is selected
                if (selectedRow >= 0) {
                    // Retrieve the product details from the selected row
                    String productId = (String) tableModel.getValueAt(selectedRow, 0);

                    // Find the selected product in the product list
                    Product selectedProduct = findProductById(productId);

                    if (selectedProduct != null) {
                        // Add the selected product to the shopping cart
                        shoppingCart.addProduct(selectedProduct);
                        selectedProduct.setNoOfAvailableItems(selectedProduct.getNoOfAvailableItems()-1);

                        // Optionally, display a message to the user
                        JOptionPane.showMessageDialog(frame, "Product added to the shopping cart!");
                    } else {
                        // Product not found, display an error message if needed
                        JOptionPane.showMessageDialog(frame, "Error: Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Add the "Add to Cart" button to the main panel
        panel.add(addToCartButton);

        // Add the panel to the content pane of the frame
        frame.getContentPane().add(panel);

        // Set the size of the frame
        frame.setSize(1000, 600);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
    private static void displaySelectedProductDetails(int selectedRow) {
        // Retrieve the product details from the selected row
        String productID = (String) tableModel.getValueAt(selectedRow, 0);

        // Find the corresponding product from the productList
        Product selectedProduct = findProductById(productID);

        // Display the product details in the JTextArea
            if (selectedProduct != null & selectedProduct instanceof Electronics) {
                String electronicsDetails = String.format(" Selected Product Details:\n\n" +
                                " Product ID: %s\n\n" +
                                " Category: %s\n\n" +
                                " Name: %s\n\n" +
                                " Brand: %s\n\n" +
                                " Warranty Period: %s\n\n"+
                                " Items Available: %d" ,
                        selectedProduct.getProductID(), selectedProduct.getCategory(),selectedProduct.getProductName(),
                        ((Electronics) selectedProduct).getBrand(), ((Electronics) selectedProduct).getWarrantyPeriod(),selectedProduct.getNoOfAvailableItems());

                productDetailsTextArea.setText(electronicsDetails);
            }

        Product selectedclProduct = findProductById(productID);
                if (selectedclProduct != null & selectedclProduct instanceof Clothing) {
                    String clothingDetails = String.format(" Selected Product Details:\n\n" +
                                    " Product ID: %s\n\n" +
                                    " Category: %s\n\n" +
                                    " Name: %s\n\n" +
                                    " Size: %s\n\n" +
                                    " Color: %s\n\n"+
                                    " Items Available: %d" ,
                            selectedProduct.getProductID(), selectedProduct.getCategory(), selectedProduct.getProductName(),
                            ((Clothing) selectedProduct).getSize(), ((Clothing) selectedProduct).getColour(),selectedProduct.getNoOfAvailableItems());

                    productDetailsTextArea.setText(clothingDetails);
                }
            }

    private static Product findProductById(String productID) {
        for (Product product : WestminsterShoppingManager.productList) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }
    private static void openShoppingCartWindow() {
        JFrame shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a table model with columns: Product details, Quantity, Price
        shoppingCartTableModel = new DefaultTableModel(new Object[]{"Product", "Quantity", "Price"}, 0);

        // Create a JTable with the table model
        shoppingCartTable = new JTable(shoppingCartTableModel);

        // Add the table to a JScrollPane for scrolling if needed
        JScrollPane scrollPane = new JScrollPane(shoppingCartTable);

        // Populate the shopping cart table with data from the shopping cart
        List<ShoppingCart.CartItem> cartItems = shoppingCart.getCartItems();
        for (ShoppingCart.CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            Object[] row = {product.getProductID(), quantity,product.getPrice()+" $"};
            shoppingCartTableModel.addRow(row);
        }

// Add a JTextArea for displaying product details
        costTextArea = new JTextArea();
        costTextArea.setEditable(false);

        // Set size for the product details text area
        costTextArea.setPreferredSize(new Dimension(550, 400));

        // Add the product details text area to the main panel
        shoppingCartFrame.add(costTextArea);


        costTextArea.setText("\nTotal    "+shoppingCart.calculateTotal()+ " $"+
        "\n\nThree Items in same Category (20%)     -"+shoppingCart.calculateTotalDiscount()+ " $"+
        "\n\nFinal Total     "+shoppingCart.calculateFinalTotal()+ " $");


// Add components to the shopping cart window
        shoppingCartFrame.getContentPane().add(scrollPane);

        // Set the size of the shopping cart frame
        shoppingCartFrame.setSize(550, 400);
        // Create a JPanel and set its layout
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));

        // Add components to the panel
        newPanel.add(scrollPane);
        newPanel.add(costTextArea);

        // Add the panel to the shopping cart frame
        shoppingCartFrame.getContentPane().add(newPanel);

        // Center the shopping cart frame on the screen
        shoppingCartFrame.setLocationRelativeTo(frame);

        // Make the shopping cart frame visible
        shoppingCartFrame.setVisible(true);
    }

}

