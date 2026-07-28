import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * InventoryManager.java
 * Exercise 1 - Inventory Management System
 *
 * Manages a warehouse inventory using a HashMap.
 *
 * WHY HashMap?
 * - Direct key-based access (productId as key)
 * - O(1) average time for add, update, delete, and lookup
 * - No need to iterate to find a product by ID
 *
 * Time Complexity Analysis:
 * +-----------+-----------+---------------------------------------------+
 * | Operation | Avg Case  | Explanation                                 |
 * +-----------+-----------+---------------------------------------------+
 * | add       |   O(1)    | put() hashes the key and stores directly    |
 * | update    |   O(1)    | get() by key, then modify fields            |
 * | delete    |   O(1)    | remove() by key using hash                  |
 * | search    |   O(1)    | get() by key                                |
 * | list all  |   O(n)    | must iterate over all n entries             |
 * +-----------+-----------+---------------------------------------------+
 * Worst case for add/update/delete is O(n) due to hash collisions,
 * but this is extremely rare with a good hash function.
 */
public class InventoryManager {

    // HashMap: key = productId, value = Product object
    private final Map<Integer, Product> inventory = new HashMap<>();

    /**
     * Adds a new product to the inventory.
     * Time Complexity: O(1) average
     *
     * @param product the product to add
     */
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("  [WARN] Product ID " + product.getProductId() + " already exists. Use update instead.");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("  [ADD]  Added: " + product.getProductName() + " (ID: " + product.getProductId() + ")");
    }

    /**
     * Updates an existing product's details.
     * Time Complexity: O(1) average
     *
     * @param productId   ID of the product to update
     * @param newName     new name (null to keep existing)
     * @param newQuantity new quantity (-1 to keep existing)
     * @param newPrice    new price (-1 to keep existing)
     */
    public void updateProduct(int productId, String newName, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("  [ERROR] Product ID " + productId + " not found.");
            return;
        }
        if (newName     != null)  product.setProductName(newName);
        if (newQuantity != -1)    product.setQuantity(newQuantity);
        if (newPrice    != -1)    product.setPrice(newPrice);
        System.out.println("  [UPDATE] Updated Product ID: " + productId);
    }

    /**
     * Deletes a product from the inventory.
     * Time Complexity: O(1) average
     *
     * @param productId ID of the product to remove
     */
    public void deleteProduct(int productId) {
        Product removed = inventory.remove(productId);
        if (removed == null) {
            System.out.println("  [ERROR] Product ID " + productId + " not found.");
        } else {
            System.out.println("  [DELETE] Removed: " + removed.getProductName() + " (ID: " + productId + ")");
        }
    }

    /**
     * Retrieves a product by ID.
     * Time Complexity: O(1) average
     */
    public Product getProduct(int productId) {
        return inventory.get(productId);
    }

    /**
     * Displays all products in the inventory.
     * Time Complexity: O(n)
     */
    public void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println("  [INFO] Inventory is empty.");
            return;
        }
        String line = "  +---------+----------------------+-----------+----------------+";
        System.out.println(line);
        System.out.println("  | ID      | Name                 | Qty       | Price          |");
        System.out.println(line);
        Collection<Product> products = inventory.values();
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println(line);
        System.out.println("  Total products: " + inventory.size());
    }

    /**
     * Returns the total number of products.
     */
    public int size() {
        return inventory.size();
    }
}
