/**
 * SortingAlgorithms.java
 * Exercise 3 - Sorting Customer Orders
 *
 * Implements Bubble Sort and Quick Sort on an Order array,
 * sorting by totalPrice in ascending order.
 *
 * SORTING ALGORITHMS OVERVIEW:
 *
 * 1. BUBBLE SORT
 *    - Repeatedly compares adjacent elements and swaps if out of order.
 *    - "Bubbles" the largest element to the end in each pass.
 *    - Time: Best O(n) [already sorted], Average/Worst O(n^2)
 *    - Space: O(1)
 *    - Simple but very slow for large datasets.
 *
 * 2. INSERTION SORT
 *    - Builds sorted portion one element at a time.
 *    - Efficient for small or nearly sorted arrays.
 *    - Time: Best O(n), Average/Worst O(n^2)
 *
 * 3. QUICK SORT
 *    - Divides array around a pivot; recursively sorts sub-arrays.
 *    - Time: Best/Average O(n log n), Worst O(n^2) [bad pivot]
 *    - Space: O(log n) stack space
 *    - Generally the fastest in practice for large datasets.
 *
 * 4. MERGE SORT
 *    - Divides array in half, sorts each half, then merges.
 *    - Time: Always O(n log n) — guaranteed
 *    - Space: O(n) — needs extra array
 *    - Preferred when stability and worst-case guarantee matter.
 */
public class SortingAlgorithms {

    // ─────────────────────────────────────────────────────────────────
    //  BUBBLE SORT — O(n^2) average/worst
    // ─────────────────────────────────────────────────────────────────

    /**
     * Sorts orders by totalPrice ascending using Bubble Sort.
     * Modifies the array in place.
     *
     * @param orders array of Order objects to sort
     */
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                // Compare adjacent elements
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap
                    Order temp    = orders[j];
                    orders[j]     = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            // Optimization: stop early if no swap occurred in this pass
            if (!swapped) break;
        }
    }

    // ─────────────────────────────────────────────────────────────────
    //  QUICK SORT — O(n log n) average
    // ─────────────────────────────────────────────────────────────────

    /**
     * Public entry point for Quick Sort.
     * Sorts orders by totalPrice ascending.
     *
     * @param orders array of Order objects to sort
     */
    public static void quickSort(Order[] orders) {
        quickSort(orders, 0, orders.length - 1);
    }

    /**
     * Recursive Quick Sort implementation.
     *
     * @param orders array to sort
     * @param low    starting index
     * @param high   ending index
     */
    private static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);   // sort left of pivot
            quickSort(orders, pivotIndex + 1, high);  // sort right of pivot
        }
    }

    /**
     * Partitions the array around the pivot (last element).
     * Elements smaller than pivot go left; larger go right.
     *
     * @return final index of the pivot element
     */
    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;  // index of smaller element

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp  = orders[i];
                orders[i]   = orders[j];
                orders[j]   = temp;
            }
        }
        // Place pivot in correct position
        Order temp      = orders[i + 1];
        orders[i + 1]   = orders[high];
        orders[high]    = temp;

        return i + 1;
    }

    /**
     * Helper: prints all orders in the array.
     *
     * @param orders the array to print
     * @param label  a label for the output
     */
    public static void printOrders(Order[] orders, String label) {
        System.out.println("  " + label + ":");
        for (Order o : orders) {
            System.out.println(o);
        }
    }
}
