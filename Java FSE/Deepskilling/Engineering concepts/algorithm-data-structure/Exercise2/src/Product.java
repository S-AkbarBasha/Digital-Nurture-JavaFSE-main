/**
 * Product.java
 * Exercise 2 - E-commerce Platform Search Function
 *
 * Represents a product on the e-commerce platform.
 * Used for both linear and binary search demonstrations.
 */
public class Product {

    private int    productId;
    private String productName;
    private String category;

    public Product(int productId, String productName, String category) {
        this.productId   = productId;
        this.productName = productName;
        this.category    = category;
    }

    public int    getProductId()   { return productId; }
    public String getProductName() { return productName; }
    public String getCategory()    { return category; }

    @Override
    public String toString() {
        return String.format("Product[id=%-3d, name=%-25s, category=%s]",
                productId, productName, category);
    }
}
