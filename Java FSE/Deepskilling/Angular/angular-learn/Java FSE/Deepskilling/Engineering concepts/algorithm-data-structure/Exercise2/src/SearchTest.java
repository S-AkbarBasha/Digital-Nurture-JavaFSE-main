/**
 * SearchTest.java
 * Exercise 2 - E-commerce Platform Search Function
 *
 * Demonstrates and compares Linear Search vs Binary Search.
 */
public class SearchTest {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("   Exercise 2: E-commerce Search Function    ");
        System.out.println("==============================================\n");

        // Unsorted array — for Linear Search
        Product[] unsortedProducts = {
            new Product(305, "Smart Watch",      "Electronics"),
            new Product(102, "Running Shoes",    "Footwear"),
            new Product(450, "Coffee Maker",     "Appliances"),
            new Product(201, "Yoga Mat",         "Sports"),
            new Product(378, "Bluetooth Speaker","Electronics"),
            new Product(110, "Sunglasses",       "Accessories"),
            new Product(500, "Laptop Stand",     "Electronics"),
            new Product(233, "Water Bottle",     "Sports"),
        };

        // Sorted array (by productId) — required for Binary Search
        Product[] sortedProducts = {
            new Product(102, "Running Shoes",    "Footwear"),
            new Product(110, "Sunglasses",       "Accessories"),
            new Product(201, "Yoga Mat",         "Sports"),
            new Product(233, "Water Bottle",     "Sports"),
            new Product(305, "Smart Watch",      "Electronics"),
            new Product(378, "Bluetooth Speaker","Electronics"),
            new Product(450, "Coffee Maker",     "Appliances"),
            new Product(500, "Laptop Stand",     "Electronics"),
        };

        // ── Test 1: Linear Search — found (middle element) ──────────
        System.out.println("--- Test 1: Linear Search (target found) ---");
        Product result = SearchAlgorithms.linearSearch(unsortedProducts, 378);
        if (result != null) System.out.println("  Result: " + result);

        // ── Test 2: Linear Search — worst case (last element) ───────
        System.out.println("\n--- Test 2: Linear Search (worst case - last element) ---");
        result = SearchAlgorithms.linearSearch(unsortedProducts, 233);
        if (result != null) System.out.println("  Result: " + result);

        // ── Test 3: Linear Search — not found ───────────────────────
        System.out.println("\n--- Test 3: Linear Search (not found) ---");
        result = SearchAlgorithms.linearSearch(unsortedProducts, 999);
        if (result == null) System.out.println("  Result: Product not found.");

        // ── Test 4: Binary Search — found ───────────────────────────
        System.out.println("\n--- Test 4: Binary Search (target found) ---");
        result = SearchAlgorithms.binarySearch(sortedProducts, 378);
        if (result != null) System.out.println("  Result: " + result);

        // ── Test 5: Binary Search — best case (middle) ──────────────
        System.out.println("\n--- Test 5: Binary Search (best case - middle element) ---");
        result = SearchAlgorithms.binarySearch(sortedProducts, 305);
        if (result != null) System.out.println("  Result: " + result);

        // ── Test 6: Binary Search — not found ───────────────────────
        System.out.println("\n--- Test 6: Binary Search (not found) ---");
        result = SearchAlgorithms.binarySearch(sortedProducts, 999);
        if (result == null) System.out.println("  Result: Product not found.");

        // ── Comparison Summary ───────────────────────────────────────
        System.out.println("\n--- Time Complexity Comparison ---");
        System.out.println("  Algorithm      | Best  | Average | Worst  | Sorted Required?");
        System.out.println("  ---------------+-------+---------+--------+-----------------");
        System.out.println("  Linear Search  | O(1)  | O(n)    | O(n)   | No");
        System.out.println("  Binary Search  | O(1)  | O(log n)| O(log n)| YES");

        System.out.println("\n  Recommendation for E-commerce Platform:");
        System.out.println("  -> Use Binary Search when searching by productId (sorted, large catalog).");
        System.out.println("  -> For 1,000,000 products: Linear = 1,000,000 steps vs Binary = ~20 steps.");
        System.out.println("  -> Use Linear Search for unsorted/text-based searches (e.g., by name).");

        System.out.println("\n==============================================");
        System.out.println("               Test Completed                ");
        System.out.println("==============================================");
    }
}
