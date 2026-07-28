/**
 * FinancialForecasting.java
 * Exercise 7 - Financial Forecasting
 *
 * Provides recursive, memoized, and iterative solutions to forecast 
 * future values based on initial values and constant or varying growth rates.
 */
public class FinancialForecasting {

    // =========================================================================
    // SECTION 1: CONSTANT GROWTH RATE ALGORITHMS
    // =========================================================================

    /**
     * Calculates future value using a Naive Recursive approach.
     * Formula: FV(n) = FV(n-1) * (1 + growthRate)
     *
     * Time Complexity: O(n) - Since there are n recursive calls.
     * Space Complexity: O(n) - Requires n stack frames on the call stack.
     *
     * @param presentValue The initial investment/value.
     * @param growthRate   The constant growth rate per period (e.g., 0.05 for 5%).
     * @param periods      The number of forecasting periods (years, months, etc.).
     * @return The forecasted future value.
     */
    public static double calculateFutureValueRecursive(double presentValue, double growthRate, int periods) {
        // Base case: if 0 periods remaining, value is the current value
        if (periods <= 0) {
            return presentValue;
        }
        // Recursive step
        return (1 + growthRate) * calculateFutureValueRecursive(presentValue, growthRate, periods - 1);
    }

    /**
     * Calculates future value using a Memoized Recursive approach (Top-Down Dynamic Programming).
     * Useful for retrieving precalculated intermediate year values in O(1) time
     * and reducing overall complexity when generating complete forecast tables.
     *
     * Time Complexity: O(n) - If not computed, resolves recursively. If computed, O(1).
     * Space Complexity: O(n) - For the memo cache array and the recursive call stack.
     *
     * @param presentValue The initial investment/value.
     * @param growthRate   The constant growth rate per period.
     * @param periods      The number of forecasting periods.
     * @param memo         An array of size (periods + 1) to store cached results.
     * @return The forecasted future value.
     */
    public static double calculateFutureValueMemoized(double presentValue, double growthRate, int periods, double[] memo) {
        // Base case
        if (periods <= 0) {
            return presentValue;
        }

        // Return cached result if already calculated
        if (memo[periods] != 0.0) {
            return memo[periods];
        }

        // Recursive computation and storing in memo array
        memo[periods] = (1 + growthRate) * calculateFutureValueMemoized(presentValue, growthRate, periods - 1, memo);
        return memo[periods];
    }

    /**
     * Calculates future value using an Iterative approach (Bottom-Up Dynamic Programming).
     * Highly optimized: completely avoids the stack frame overhead, making it immune 
     * to StackOverflowError even for millions of periods.
     *
     * Time Complexity: O(n) - Runs a single loop n times.
     * Space Complexity: O(1) - Uses constant auxiliary space.
     *
     * @param presentValue The initial investment/value.
     * @param growthRate   The constant growth rate per period.
     * @param periods      The number of forecasting periods.
     * @return The forecasted future value.
     */
    public static double calculateFutureValueIterative(double presentValue, double growthRate, int periods) {
        double futureValue = presentValue;
        for (int i = 0; i < periods; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    // =========================================================================
    // SECTION 2: VARYING GROWTH RATE ALGORITHMS (HISTORICAL RATES)
    // =========================================================================

    /**
     * Calculates future value using a Naive Recursive approach with varying growth rates.
     * Useful when growth rate varies year-over-year based on historical data.
     *
     * Time Complexity: O(n) - Where n is the number of periods.
     * Space Complexity: O(n) - Stack frames depth.
     *
     * @param presentValue The initial investment/value.
     * @param growthRates  An array of growth rates for each period.
     * @param periods      The number of periods to forecast.
     * @return The forecasted future value.
     */
    public static double calculateFutureValueVarying(double presentValue, double[] growthRates, int periods) {
        // Base Case
        if (periods <= 0 || periods > growthRates.length) {
            return presentValue;
        }
        // Recursive Step: V(t) = V(t-1) * (1 + r[t-1])
        return calculateFutureValueVarying(presentValue, growthRates, periods - 1) * (1 + growthRates[periods - 1]);
    }

    /**
     * Calculates future value using an Iterative approach with varying growth rates.
     * Optimized: O(n) time complexity and O(1) space complexity.
     *
     * @param presentValue The initial investment/value.
     * @param growthRates  An array of growth rates for each period.
     * @param periods      The number of periods to forecast.
     * @return The forecasted future value.
     */
    public static double calculateFutureValueVaryingIterative(double presentValue, double[] growthRates, int periods) {
        double futureValue = presentValue;
        int limit = Math.min(periods, growthRates.length);
        for (int i = 0; i < limit; i++) {
            futureValue *= (1 + growthRates[i]);
        }
        return futureValue;
    }
}
