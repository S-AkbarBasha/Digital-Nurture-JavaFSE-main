/**
 * WordDocument.java
 *
 * Concrete implementation of Document representing a Word (.docx) document.
 */
public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("  [Word]  Opening Word document (.docx)...");
    }

    @Override
    public void save() {
        System.out.println("  [Word]  Saving Word document (.docx)...");
    }

    @Override
    public void close() {
        System.out.println("  [Word]  Closing Word document (.docx).");
    }

    @Override
    public String getDocumentType() {
        return "Word Document (.docx)";
    }
}
