/**
 * SortingTest.java
 * Exercise 3 - Sorting Customer Orders
 *
 * Demonstrates and compares Bubble Sort and Quick Sort
 * on a list of customer orders sorted by totalPrice.
 */
public class SortingTest {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("   Exercise 3: Sorting Customer Orders       ");
        System.out.println("==============================================\n");

        // Original unsorted orders
        Order[] originalOrders = {
            new Order(1001, "Alice Johnson",   549.99),
            new Order(1002, "Bob Smith",        89.50),
            new Order(1003, "Carol White",    1200.00),
            new Order(1004, "David Brown",     320.75),
            new Order(1005, "Eva Green",        45.00),
            new Order(1006, "Frank Lee",       879.25),
            new Order(1007, "Grace Hall",      210.00),
            new Order(1008, "Henry Clark",    3500.00),
        };

        System.out.println("--- Original (Unsorted) Orders ---");
        SortingAlgorithms.printOrders(originalOrders, "Unsorted");

        // ── Bubble Sort ───────────────────────────────────────────────
        // Make a copy so both sorts start from the same unsorted data
        Order[] bubbleOrders = copyOrders(originalOrders);
        System.out.println("\n--- Bubble Sort: Sorting by Total Price ---");
        long startTime = System.nanoTime();
        SortingAlgorithms.bubbleSort(bubbleOrders);
        long bubbleTime = System.nanoTime() - startTime;
        SortingAlgorithms.printOrders(bubbleOrders, "After Bubble Sort (ascending)");
        System.out.println("  Time taken: " + bubbleTime + " ns");

        // ── Quick Sort ────────────────────────────────────────────────
        Order[] quickOrders = copyOrders(originalOrders);
        System.out.println("\n--- Quick Sort: Sorting by Total Price ---");
        startTime = System.nanoTime();
        SortingAlgorithms.quickSort(quickOrders);
        long quickTime = System.nanoTime() - startTime;
        SortingAlgorithms.printOrders(quickOrders, "After Quick Sort (ascending)");
        System.out.println("  Time taken: " + quickTime + " ns");

        // ── Verify both produce same result ───────────────────────────
        System.out.println("\n--- Verification: Both Sorts Match? ---");
        boolean match = true;
        for (int i = 0; i < bubbleOrders.length; i++) {
            if (bubbleOrders[i].getOrderId() != quickOrders[i].getOrderId()) {
                match = false;
                break;
            }
        }
        System.out.println("  Result: " + (match ? "YES — both sorts produced identical ordering." : "NO — mismatch found!"));

        // ── Time Complexity Comparison ────────────────────────────────
        System.out.println("\n--- Time Complexity Comparison ---");
        System.out.println("  Algorithm    | Best     | Average    | Worst    | Space");
        System.out.println("  -------------+----------+------------+----------+-------");
        System.out.println("  Bubble Sort  | O(n)     | O(n^2)     | O(n^2)   | O(1)");
        System.out.println("  Quick Sort   | O(n logn)| O(n log n) | O(n^2)   | O(log n)");
        System.out.println("  Merge Sort   | O(n logn)| O(n log n) | O(n logn)| O(n)");
        System.out.println("  Insertion    | O(n)     | O(n^2)     | O(n^2)   | O(1)");

        System.out.println("\n  Why Quick Sort is preferred over Bubble Sort:");
        System.out.println("  1. For n=1000 orders: Bubble = ~1,000,000 ops vs Quick = ~10,000 ops");
        System.out.println("  2. Quick Sort uses divide-and-conquer — much fewer comparisons on average");
        System.out.println("  3. Bubble Sort is O(n^2) even in average case — unusable for large datasets");
        System.out.println("  4. Quick Sort is cache-friendly and works in-place (no extra array needed)");
        System.out.println("  5. Real-world: Java's Arrays.sort() uses a Dual-Pivot Quick Sort internally");

        System.out.println("\n==============================================");
        System.out.println("               Test Completed                ");
        System.out.println("==============================================");
    }

    /** Creates a deep copy of an Order array for fair comparison. */
    private static Order[] copyOrders(Order[] original) {
        Order[] copy = new Order[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new Order(
                original[i].getOrderId(),
                original[i].getCustomerName(),
                original[i].getTotalPrice()
            );
        }
        return copy;
    }
}
