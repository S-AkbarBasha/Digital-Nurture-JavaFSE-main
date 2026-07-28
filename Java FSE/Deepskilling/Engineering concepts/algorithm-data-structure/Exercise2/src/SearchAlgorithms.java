/**
 * SearchAlgorithms.java
 * Exercise 2 - E-commerce Platform Search Function
 *
 * Implements Linear Search and Binary Search on a Product array.
 *
 * BIG O NOTATION RECAP:
 * Big O describes how the runtime grows as input size (n) increases.
 *
 * LINEAR SEARCH:
 *   - Best Case  : O(1)    - target is the first element
 *   - Average    : O(n/2) = O(n) - target is in the middle
 *   - Worst Case : O(n)    - target is last or not found
 *   - Requirement: Array can be unsorted
 *
 * BINARY SEARCH:
 *   - Best Case  : O(1)    - target is the middle element
 *   - Average    : O(log n)- halves search space each step
 *   - Worst Case : O(log n)- must reach a single element
 *   - Requirement: Array MUST be sorted by the search key
 */
public class SearchAlgorithms {

    /**
     * LINEAR SEARCH — checks every element one by one.
     * Works on unsorted arrays.
     * Time Complexity: O(n)
     *
     * @param products  array of products to search
     * @param targetId  the productId to find
     * @return the matching Product, or null if not found
     */
    public static Product linearSearch(Product[] products, int targetId) {
        System.out.println("  [Linear Search] Searching for ID: " + targetId);
        int comparisons = 0;

        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductId() == targetId) {
                System.out.println("  Found at index " + i + " after " + comparisons + " comparison(s).");
                return products[i];
            }
        }
        System.out.println("  Not found after " + comparisons + " comparison(s).");
        return null;
    }

    /**
     * BINARY SEARCH — divides the sorted array in half each step.
     * Requires array sorted by productId.
     * Time Complexity: O(log n)
     *
     * @param products  sorted array of products (by productId ascending)
     * @param targetId  the productId to find
     * @return the matching Product, or null if not found
     */
    public static Product binarySearch(Product[] products, int targetId) {
        System.out.println("  [Binary Search] Searching for ID: " + targetId);
        int low = 0, high = products.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;   // avoids integer overflow
            comparisons++;
            int midId = products[mid].getProductId();

            if (midId == targetId) {
                System.out.println("  Found at index " + mid + " after " + comparisons + " comparison(s).");
                return products[mid];
            } else if (midId < targetId) {
                low = mid + 1;   // target is in the right half
            } else {
                high = mid - 1;  // target is in the left half
            }
        }
        System.out.println("  Not found after " + comparisons + " comparison(s).");
        return null;
    }
}
