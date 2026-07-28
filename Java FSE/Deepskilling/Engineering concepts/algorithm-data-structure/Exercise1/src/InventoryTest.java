/**
 * InventoryTest.java
 * Exercise 1 - Inventory Management System
 *
 * Demonstrates add, update, delete, and retrieve operations
 * on the warehouse inventory.
 */
public class InventoryTest {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("   Exercise 1: Inventory Management System   ");
        System.out.println("==============================================\n");

        InventoryManager manager = new InventoryManager();

        // ── Step 1: Add products ──────────────────────────────────────
        System.out.println("--- Adding Products ---");
        manager.addProduct(new Product(101, "Wireless Mouse",      150, 29.99));
        manager.addProduct(new Product(102, "Mechanical Keyboard",  75, 89.99));
        manager.addProduct(new Product(103, "USB-C Hub",           200, 45.00));
        manager.addProduct(new Product(104, "Monitor Stand",        60, 55.50));
        manager.addProduct(new Product(105, "Webcam HD",           120, 79.00));

        // Try adding duplicate
        manager.addProduct(new Product(101, "Duplicate Mouse", 10, 5.00));

        System.out.println("\n--- Current Inventory ---");
        manager.displayAll();

        // ── Step 2: Update products ───────────────────────────────────
        System.out.println("\n--- Updating Products ---");
        manager.updateProduct(102, "Mechanical Keyboard RGB", 80, 99.99);
        manager.updateProduct(103, null, 180, -1);   // only quantity updated
        manager.updateProduct(999, null, 10, -1);    // non-existent

        System.out.println("\n--- Inventory After Updates ---");
        manager.displayAll();

        // ── Step 3: Delete products ───────────────────────────────────
        System.out.println("\n--- Deleting Products ---");
        manager.deleteProduct(104);
        manager.deleteProduct(999); // non-existent

        System.out.println("\n--- Inventory After Deletion ---");
        manager.displayAll();

        // ── Step 4: Retrieve a single product ────────────────────────
        System.out.println("\n--- Retrieve Single Product (ID: 105) ---");
        Product found = manager.getProduct(105);
        if (found != null) {
            System.out.println(found);
        }

        // ── Step 5: Time Complexity Summary ──────────────────────────
        System.out.println("\n--- Time Complexity Analysis (HashMap) ---");
        System.out.println("  Add    -> O(1) average  [hash-based put()]");
        System.out.println("  Update -> O(1) average  [hash-based get() + field set]");
        System.out.println("  Delete -> O(1) average  [hash-based remove()]");
        System.out.println("  Search -> O(1) average  [hash-based get()]");
        System.out.println("  List   -> O(n)           [iterate all entries]");
        System.out.println("\n  Optimization Tips:");
        System.out.println("  - Use LinkedHashMap to maintain insertion order");
        System.out.println("  - Use TreeMap to keep products sorted by ID at O(log n) cost");
        System.out.println("  - Index by productName too (second HashMap) for name-based O(1) search");

        System.out.println("\n==============================================");
        System.out.println("               Test Completed                ");
        System.out.println("==============================================");
    }
}
