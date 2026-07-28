/**
 * ComputerBuilderTest.java
 *
 * Test class demonstrating the Builder Pattern.
 * Creates several different Computer configurations using
 * the fluent Builder API — from a minimal budget PC
 * to a full high-end gaming rig.
 */
public class ComputerBuilderTest {

    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println("    Builder Pattern - Computer Config Test   ");
        System.out.println("=============================================");

        // ── Config 1: Minimal Budget PC ──────────────────────────────
        System.out.println("\n[Config 1] Minimal Budget PC");
        Computer budgetPC = new Computer.Builder("Intel Core i3", 8)
                .storage(256, "HDD")
                .operatingSystem("Linux Ubuntu")
                .wifi(true)
                .build();
        System.out.println(budgetPC);

        // ── Config 2: Standard Office Laptop ─────────────────────────
        System.out.println("\n[Config 2] Standard Office Laptop");
        Computer officeLaptop = new Computer.Builder("Intel Core i5", 16)
                .storage(512, "SSD")
                .operatingSystem("Windows 11")
                .wifi(true)
                .bluetooth(true)
                .powerSupply("65W Adapter")
                .build();
        System.out.println(officeLaptop);

        // ── Config 3: High-End Gaming Rig ────────────────────────────
        System.out.println("\n[Config 3] High-End Gaming Rig");
        Computer gamingPC = new Computer.Builder("Intel Core i9-14900K", 64)
                .storage(2000, "NVMe SSD")
                .gpu("NVIDIA RTX 4090 24GB")
                .operatingSystem("Windows 11 Pro")
                .wifi(true)
                .bluetooth(true)
                .powerSupply("1000W Platinum")
                .coolingSystem("360mm Liquid Cooling")
                .build();
        System.out.println(gamingPC);

        // ── Config 4: Developer Workstation ──────────────────────────
        System.out.println("\n[Config 4] Developer Workstation");
        Computer devWorkstation = new Computer.Builder("AMD Ryzen 9 7950X", 128)
                .storage(4000, "SSD")
                .gpu("NVIDIA RTX 3060")
                .operatingSystem("macOS Ventura")
                .wifi(true)
                .bluetooth(true)
                .powerSupply("850W Gold")
                .coolingSystem("Air Tower Cooler")
                .build();
        System.out.println(devWorkstation);

        // ── Config 5: Basic Server (no GPU, no OS) ───────────────────
        System.out.println("\n[Config 5] Headless Server");
        Computer server = new Computer.Builder("AMD EPYC 9654", 512)
                .storage(10000, "SSD RAID")
                .powerSupply("1600W Titanium")
                .coolingSystem("Data Center Cooling")
                .build();
        System.out.println(server);

        // ── Verification: Different configs are different objects ─────
        System.out.println("\n-- Object Identity Check --");
        System.out.println("  budgetPC    hashCode : " + System.identityHashCode(budgetPC));
        System.out.println("  gamingPC    hashCode : " + System.identityHashCode(gamingPC));
        System.out.println("  devStation  hashCode : " + System.identityHashCode(devWorkstation));
        System.out.println("  (All different — each build() creates a new Computer object)");

        System.out.println("\n=============================================");
        System.out.println("             Test Completed                  ");
        System.out.println("=============================================");
    }
}
