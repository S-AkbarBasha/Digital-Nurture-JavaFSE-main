/**
 * Product.java
 * Exercise 1 - Inventory Management System
 *
 * Represents a product stored in the warehouse inventory.
 */
public class Product {

    private int    productId;
    private String productName;
    private int    quantity;
    private double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId   = productId;
        this.productName = productName;
        this.quantity    = quantity;
        this.price       = price;
    }

    // Getters
    public int    getProductId()   { return productId; }
    public String getProductName() { return productName; }
    public int    getQuantity()    { return quantity; }
    public double getPrice()       { return price; }

    // Setters
    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(int quantity)           { this.quantity = quantity; }
    public void setPrice(double price)              { this.price = price; }

    @Override
    public String toString() {
        return String.format("  | ID: %-4d | %-20s | Qty: %-5d | Price: $%-8.2f |",
                productId, productName, quantity, price);
    }
}
