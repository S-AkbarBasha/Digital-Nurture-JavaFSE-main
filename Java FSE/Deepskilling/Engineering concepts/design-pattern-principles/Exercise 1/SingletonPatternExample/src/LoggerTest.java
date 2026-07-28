/**
 * LoggerTest.java
 *
 * Test class to verify that only one instance of Logger
 * is created and reused throughout the application.
 */
public class LoggerTest {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("   Singleton Pattern - Logger Test    ");
        System.out.println("======================================\n");

        // --- Test 1: Obtain two references to Logger ---
        System.out.println(">> Obtaining Logger instance (first call)...");
        Logger logger1 = Logger.getInstance();

        System.out.println(">> Obtaining Logger instance (second call)...");
        Logger logger2 = Logger.getInstance();

        // --- Test 2: Verify both references point to the same object ---
        System.out.println("\n-- Singleton Verification --");
        System.out.println("logger1 hashCode : " + System.identityHashCode(logger1));
        System.out.println("logger2 hashCode : " + System.identityHashCode(logger2));

        if (logger1 == logger2) {
            System.out.println("✔ SUCCESS: Both references point to the SAME Logger instance.\n");
        } else {
            System.out.println("✘ FAILURE: Different Logger instances were created!\n");
        }

        // --- Test 3: Use the logger for actual logging ---
        System.out.println("-- Using logger1 --");
        logger1.log("Application has started.");
        logger1.warn("Low memory detected.");
        logger1.error("Unable to connect to database.");

        System.out.println("\n-- Using logger2 --");
        logger2.log("Processing user request.");
        logger2.warn("Deprecated API called.");
        logger2.error("Null pointer encountered.");

        // --- Test 4: Third reference obtained from a different method ---
        System.out.println("\n>> Obtaining Logger instance (third call from helper method)...");
        Logger logger3 = getLoggerFromHelper();
        System.out.println("logger3 hashCode : " + System.identityHashCode(logger3));

        if (logger1 == logger3) {
            System.out.println("✔ SUCCESS: Helper method also returned the SAME Logger instance.");
        } else {
            System.out.println("✘ FAILURE: Helper method returned a different instance!");
        }

        System.out.println("\n======================================");
        System.out.println("           Test Completed             ");
        System.out.println("======================================");
    }

    /**
     * Simulates a different part of the application requesting the Logger.
     *
     * @return the single Logger instance
     */
    private static Logger getLoggerFromHelper() {
        return Logger.getInstance();
    }
}
