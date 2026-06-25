/**
 * PdfDocument.java
 *
 * Concrete implementation of Document representing a PDF (.pdf) document.
 */
public class PdfDocument implements Document {

    @Override
    public void open() {
        System.out.println("  [PDF]   Opening PDF document (.pdf)...");
    }

    @Override
    public void save() {
        System.out.println("  [PDF]   Saving PDF document (.pdf)...");
    }

    @Override
    public void close() {
        System.out.println("  [PDF]   Closing PDF document (.pdf).");
    }

    @Override
    public String getDocumentType() {
        return "PDF Document (.pdf)";
    }
}
