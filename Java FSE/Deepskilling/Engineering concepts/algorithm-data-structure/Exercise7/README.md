# Exercise 7: Financial Forecasting

This exercise implements a financial forecasting tool to predict future values based on past data (such as present value and growth rates), comparing recursive, memoized, and iterative approaches.

---

## 1. Understanding Recursive Algorithms

### What is Recursion?
**Recursion** is a programming technique where a method solves a problem by calling itself one or more times with simpler or smaller sub-instances of the same problem. 

Every recursive algorithm must consist of two essential parts:
1. **Base Case**: The condition under which the recursion stops. Without a base case, recursion continues infinitely, eventually resulting in a `StackOverflowError`.
2. **Recursive Step**: The part of the method where the problem is broken down into a smaller instance, and the method calls itself. This step must always move the input closer to the base case.

### How Recursion Simplifies Problems
Recursion matches the mathematical induction principle. It simplifies problems that:
- Have a naturally recursive structure (e.g., directory structures, tree structures, nested lists).
- Can be defined using a recurrence relation (e.g., Factorials, Fibonacci, Merge Sort, Quick Sort).
- Would otherwise require complex, error-prone manual stack management.

In financial forecasting, the value of an asset in year $N$ is directly built upon the value in year $N-1$. Thus, we can express it recursively:
$$\text{Value}(N) = \text{Value}(N-1) \times (1 + \text{growthRate})$$

---

## 2. Theoretical Analysis of the Recursive Solution

### Naive Recursive Complexity
For a naive implementation where we calculate the value of year $N$:
- **Time Complexity**: $\mathcal{O}(N)$
  - There are $N$ recursive calls, each executing in $\mathcal{O}(1)$ time.
- **Space Complexity**: $\mathcal{O}(N)$
  - Each recursive call adds a stack frame to the call stack. For a large number of periods (e.g., forecasting monthly values over 50 years, which is $600$ periods), this could exhaust stack memory.

### Excessive Computation Scenarios
The naive recursive solution suffers from two major inefficiencies:

1. **Stack Overflow Risk**: Since the JVM has a limited stack size, deep recursions ($\mathcal{O}(N)$ space) will crash with a `java.lang.StackOverflowError`.
2. **Redundant Recomputations**: If we want to display a forecast table for every year from $1$ to $N$, calling `calculateFutureValue(PV, r, year)` for each year results in:
   $$\sum_{i=1}^{N} i = \frac{N(N+1)}{2} = \mathcal{O}(N^2) \text{ operations}$$
   This recomputes identical states (like year 1, 2, 3...) repeatedly.

---

## 3. Optimization Strategies

### A. Memoization (Top-down Dynamic Programming)
By storing the results of intermediate years in a cache (like an array or hash map), we can reuse them instead of recalculating.
- **Time Complexity**: Reduces sequential table generation to $\mathcal{O}(N)$ overall.
- **Space Complexity**: $\mathcal{O}(N)$ for the cache and call stack.

### B. Iterative Approach (Bottom-up Dynamic Programming)
We can eliminate recursion completely by using a loop.
- **Time Complexity**: $\mathcal{O}(N)$
- **Space Complexity**: $\mathcal{O}(1)$ (uses constant memory, completely avoiding stack frames).
- **Benefit**: Immune to stack overflows, highly cache-friendly, and uses minimal resources.
