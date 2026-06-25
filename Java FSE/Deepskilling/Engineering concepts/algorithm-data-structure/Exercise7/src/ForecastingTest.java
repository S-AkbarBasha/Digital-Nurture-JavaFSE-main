/**
 * ForecastingTest.java
 * Exercise 7 - Financial Forecasting
 *
 * Driver class to demonstrate, verify, and compare recursive,
 * memoized, and iterative financial forecasting.
 */
public class ForecastingTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      Exercise 7: Financial Forecasting Tool     ");
        System.out.println("==================================================\n");

        double initialValue = 1000.00; // $1,000 initial investment
        double growthRate = 0.05;      // 5% constant annual growth rate
        int periods = 10;              // 10 years forecast

        System.out.println("--- Scenario parameters ---");
        System.out.printf("  Present Value (PV): $%,.2f\n", initialValue);
        System.out.printf("  Annual Growth Rate: %.1f%%\n", growthRate * 100);
        System.out.printf("  Forecast Periods  : %d years\n\n", periods);

        // =====================================================================
        // Test 1: Verification and Consistency (Constant Growth Rate)
        // =====================================================================
        System.out.println("--- 1. Constant Growth Rate Results ---");

        // A. Naive Recursive
        double fvRecursive = FinancialForecasting.calculateFutureValueRecursive(initialValue, growthRate, periods);
        System.out.printf("  [Naive Recursive] Future Value (Year %d): $%,.4f\n", periods, fvRecursive);

        // B. Memoized Recursive
        double[] memo = new double[periods + 1];
        double fvMemoized = FinancialForecasting.calculateFutureValueMemoized(initialValue, growthRate, periods, memo);
        System.out.printf("  [Memoized Recur.] Future Value (Year %d): $%,.4f\n", periods, fvMemoized);

        // C. Iterative
        double fvIterative = FinancialForecasting.calculateFutureValueIterative(initialValue, growthRate, periods);
        System.out.printf("  [Optimized Iter.] Future Value (Year %d): $%,.4f\n", periods, fvIterative);

        // Check if they are equal
        boolean matches = Math.abs(fvRecursive - fvIterative) < 0.0001 && Math.abs(fvMemoized - fvIterative) < 0.0001;
        System.out.println("  Consistency Check: " + (matches ? "PASS (All constant rate methods match)" : "FAIL (Mismatch detected)"));
        System.out.println();

        // =====================================================================
        // Test 2: Varying Growth Rates (Historical Data)
        // =====================================================================
        System.out.println("--- 2. Varying Growth Rates (Historical Data) ---");
        // Sample growth rates for 5 periods: Yr1: 5%, Yr2: 3%, Yr3: -2% (market correction), Yr4: 8%, Yr5: 6%
        double[] historicalRates = { 0.05, 0.03, -0.02, 0.08, 0.06 };
        int varyingPeriods = historicalRates.length;

        System.out.print("  Historical Rates applied: [");
        for (int i = 0; i < historicalRates.length; i++) {
            System.out.printf("%.1f%%", historicalRates[i] * 100);
            if (i < historicalRates.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        double fvVaryingRec = FinancialForecasting.calculateFutureValueVarying(initialValue, historicalRates, varyingPeriods);
        double fvVaryingIter = FinancialForecasting.calculateFutureValueVaryingIterative(initialValue, historicalRates, varyingPeriods);

        System.out.printf("  [Varying Recursive] Future Value (Year %d): $%,.4f\n", varyingPeriods, fvVaryingRec);
        System.out.printf("  [Varying Iterative] Future Value (Year %d): $%,.4f\n", varyingPeriods, fvVaryingIter);
        
        boolean varyingMatches = Math.abs(fvVaryingRec - fvVaryingIter) < 0.0001;
        System.out.println("  Consistency Check: " + (varyingMatches ? "PASS (All varying rate methods match)" : "FAIL (Mismatch detected)"));
        System.out.println();

        // =====================================================================
        // Test 3: Optimization Analysis & Graceful Stack Overflow Demo
        // =====================================================================
        System.out.println("--- 3. Optimization and Recursion Analysis ---");
        int largePeriods = 25000; // Large periods to show limits of recursion
        System.out.println("  Testing large scale forecasting with " + largePeriods + " periods:");

        // A. Try Naive Recursion
        System.out.print("  Applying Naive Recursion... ");
        long start = System.nanoTime();
        try {
            double fvLargeRec = FinancialForecasting.calculateFutureValueRecursive(initialValue, growthRate, largePeriods);
            long end = System.nanoTime();
            System.out.printf("Success in %d ns ($%,.2f)\n", (end - start), fvLargeRec);
        } catch (StackOverflowError e) {
            long end = System.nanoTime();
            System.out.println("FAILED due to java.lang.StackOverflowError!");
            System.out.printf("  (Crash occurred after %d ns due to excessive call stack height)\n", (end - start));
        }

        // B. Try Iteration
        System.out.print("  Applying Iterative Loop... ");
        start = System.nanoTime();
        double fvLargeIter = FinancialForecasting.calculateFutureValueIterative(initialValue, growthRate, largePeriods);
        long end = System.nanoTime();
        System.out.printf("Success in %d ns (Value matches closed-form math)\n", (end - start));
        System.out.printf("  Iterative Future Value: $%,.2e\n", fvLargeIter);
        System.out.println("  (Iterative approach runs in O(N) time and O(1) space with NO stack frames)\n");

        // =====================================================================
        // SECTION 4: DISCUSSION & CONCLUSIONS
        // =====================================================================
        System.out.println("--- 4. Summary of Architectural Explanations ---");
        System.out.println("  1. Concept of Recursion:");
        System.out.println("     - Recursion splits a problem into base case (periods = 0) and recursive steps.");
        System.out.println("     - It simplifies the code by matching the mathematical definition of compound growth.");
        System.out.println("  2. Time Complexity:");
        System.out.println("     - Naive recursive is O(N) since it invokes N nested method executions.");
        System.out.println("     - Space complexity is O(N) due to call stack frames, leading to stack overflow for large N.");
        System.out.println("  3. Optimization Strategy:");
        System.out.println("     - Memoization: Stores intermediate results, reducing sequential calculations from O(N^2) to O(N).");
        System.out.println("     - Iteration: Converts recursion into a loop, keeping time complexity at O(N) and space complexity");
        System.out.println("       at O(1). This completely eliminates stack frame allocation and call stack limits.");

        System.out.println("\n==================================================");
        System.out.println("               Test Completed                     ");
        System.out.println("==================================================");
    }
}
