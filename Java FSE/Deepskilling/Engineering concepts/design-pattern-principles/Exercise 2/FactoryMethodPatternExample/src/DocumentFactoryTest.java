/**
 * DocumentFactoryTest.java
 *
 * Test class demonstrating the Factory Method Pattern.
 * Shows how each concrete factory creates its own document type
 * while the client code works only with the abstract Document interface.
 */
public class DocumentFactoryTest {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("   Factory Method Pattern - Document Test   ");
        System.out.println("============================================\n");

        // -------------------------------------------------------
        // Test 1: Create each factory and produce its document
        // -------------------------------------------------------
        System.out.println("--- Test 1: Creating documents via factories ---\n");

        DocumentFactory wordFactory  = new WordDocumentFactory();
        DocumentFactory pdfFactory   = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        Document wordDoc  = wordFactory.createDocument();
        Document pdfDoc   = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();

        System.out.println("  Created: " + wordDoc.getDocumentType());
        System.out.println("  Created: " + pdfDoc.getDocumentType());
        System.out.println("  Created: " + excelDoc.getDocumentType());

        // -------------------------------------------------------
        // Test 2: Use each document via the Document interface
        // -------------------------------------------------------
        System.out.println("\n--- Test 2: Word Document operations ---");
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();

        System.out.println("\n--- Test 3: PDF Document operations ---");
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();

        System.out.println("\n--- Test 4: Excel Document operations ---");
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();

        // -------------------------------------------------------
        // Test 5: Use the template method in DocumentFactory
        // -------------------------------------------------------
        System.out.println("\n--- Test 5: Using factory template method (openAndSaveDocument) ---\n");

        System.out.println("  >> WordDocumentFactory:");
        wordFactory.openAndSaveDocument();

        System.out.println("\n  >> PdfDocumentFactory:");
        pdfFactory.openAndSaveDocument();

        System.out.println("\n  >> ExcelDocumentFactory:");
        excelFactory.openAndSaveDocument();

        // -------------------------------------------------------
        // Test 6: Polymorphic usage — client depends only on abstractions
        // -------------------------------------------------------
        System.out.println("\n--- Test 6: Polymorphic factory usage ---\n");

        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            Document doc = factory.createDocument();
            System.out.println("  Factory produced: " + doc.getDocumentType());
            doc.open();
            doc.save();
            doc.close();
            System.out.println();
        }

        System.out.println("============================================");
        System.out.println("             Test Completed                 ");
        System.out.println("============================================");
    }
}
