/**
 * Logger.java
 * 
 * Singleton Design Pattern Implementation
 * 
 * This class ensures only one instance of Logger exists
 * throughout the entire application lifecycle.
 */
public class Logger {

    // Step 1: Private static instance of itself (lazy initialization)
    private static Logger instance;

    // Step 2: Private constructor to prevent instantiation from outside
    private Logger() {
        System.out.println("[Logger] Logger instance created.");
    }

    /**
     * Step 3: Public static method to get the single instance.
     * Uses double-checked locking for thread safety.
     *
     * @return the single instance of Logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * Logs an informational message.
     *
     * @param message the message to log
     */
    public void log(String message) {
        System.out.println("[INFO]  " + message);
    }

    /**
     * Logs a warning message.
     *
     * @param message the warning message to log
     */
    public void warn(String message) {
        System.out.println("[WARN]  " + message);
    }

    /**
     * Logs an error message.
     *
     * @param message the error message to log
     */
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
