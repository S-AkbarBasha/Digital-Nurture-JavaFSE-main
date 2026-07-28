/**
 * Computer.java
 *
 * Product class representing a Computer with multiple optional parts.
 * Uses the Builder Pattern via a static nested Builder class.
 *
 * The Computer object can only be created through its Builder —
 * the constructor is private to enforce this.
 */
public class Computer {

    // ── Required attributes ──────────────────────────────────────────
    private final String cpu;       // e.g. "Intel Core i9"
    private final int    ramGB;     // e.g. 32

    // ── Optional attributes ──────────────────────────────────────────
    private final int    storageGB;        // e.g. 1000
    private final String storageType;      // "SSD" or "HDD"
    private final String gpu;              // e.g. "NVIDIA RTX 4090"
    private final String operatingSystem;  // e.g. "Windows 11"
    private final boolean bluetoothEnabled;
    private final boolean wifiEnabled;
    private final String  powerSupply;     // e.g. "750W Gold"
    private final String  coolingSystem;   // e.g. "Liquid Cooling"

    // ── Private constructor — only Builder can call this ─────────────
    private Computer(Builder builder) {
        this.cpu               = builder.cpu;
        this.ramGB             = builder.ramGB;
        this.storageGB         = builder.storageGB;
        this.storageType       = builder.storageType;
        this.gpu               = builder.gpu;
        this.operatingSystem   = builder.operatingSystem;
        this.bluetoothEnabled  = builder.bluetoothEnabled;
        this.wifiEnabled       = builder.wifiEnabled;
        this.powerSupply       = builder.powerSupply;
        this.coolingSystem     = builder.coolingSystem;
    }

    // ── Getters ──────────────────────────────────────────────────────
    public String  getCpu()              { return cpu; }
    public int     getRamGB()            { return ramGB; }
    public int     getStorageGB()        { return storageGB; }
    public String  getStorageType()      { return storageType; }
    public String  getGpu()              { return gpu; }
    public String  getOperatingSystem()  { return operatingSystem; }
    public boolean isBluetoothEnabled()  { return bluetoothEnabled; }
    public boolean isWifiEnabled()       { return wifiEnabled; }
    public String  getPowerSupply()      { return powerSupply; }
    public String  getCoolingSystem()    { return coolingSystem; }

    /**
     * Displays a formatted summary of this Computer's configuration.
     */
    @Override
    public String toString() {
        String line = "  +---------------------------------------+";
        return  "\n" + line + "\n"
              + "  |       Computer Configuration          |\n"
              + line + "\n"
              + "  |  CPU            : " + pad(cpu)              + " |\n"
              + "  |  RAM            : " + pad(ramGB + " GB")    + " |\n"
              + "  |  Storage        : " + pad(storageGB + " GB " + storageType) + " |\n"
              + "  |  GPU            : " + pad(gpu != null            ? gpu               : "Integrated") + " |\n"
              + "  |  OS             : " + pad(operatingSystem != null? operatingSystem   : "None")       + " |\n"
              + "  |  Power Supply   : " + pad(powerSupply != null    ? powerSupply       : "Standard")   + " |\n"
              + "  |  Cooling        : " + pad(coolingSystem != null  ? coolingSystem     : "Air Cooling") + " |\n"
              + "  |  Wi-Fi          : " + pad(wifiEnabled  ? "Yes" : "No") + " |\n"
              + "  |  Bluetooth      : " + pad(bluetoothEnabled ? "Yes" : "No") + " |\n"
              + line;
    }

    /** Pads a string to a fixed width for aligned table output. */
    private String pad(String value) {
        return String.format("%-19s", value);
    }

    // ════════════════════════════════════════════════════════════════
    //  Static Nested Builder Class
    // ════════════════════════════════════════════════════════════════
    public static class Builder {

        // Required
        private final String cpu;
        private final int    ramGB;

        // Optional — defaults provided
        private int     storageGB        = 256;
        private String  storageType      = "SSD";
        private String  gpu              = null;
        private String  operatingSystem  = null;
        private boolean bluetoothEnabled = false;
        private boolean wifiEnabled      = false;
        private String  powerSupply      = null;
        private String  coolingSystem    = null;

        /**
         * Builder constructor requires the two mandatory fields.
         *
         * @param cpu   the CPU model (required)
         * @param ramGB the amount of RAM in GB (required)
         */
        public Builder(String cpu, int ramGB) {
            if (cpu == null || cpu.isBlank()) {
                throw new IllegalArgumentException("CPU cannot be null or empty.");
            }
            if (ramGB <= 0) {
                throw new IllegalArgumentException("RAM must be a positive value.");
            }
            this.cpu   = cpu;
            this.ramGB = ramGB;
        }

        public Builder storage(int storageGB, String storageType) {
            this.storageGB   = storageGB;
            this.storageType = storageType;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder operatingSystem(String os) {
            this.operatingSystem = os;
            return this;
        }

        public Builder bluetooth(boolean enabled) {
            this.bluetoothEnabled = enabled;
            return this;
        }

        public Builder wifi(boolean enabled) {
            this.wifiEnabled = enabled;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Builder coolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }

        /**
         * Builds and returns the final Computer object.
         *
         * @return a fully constructed Computer
         */
        public Computer build() {
            return new Computer(this);
        }
    }
}
