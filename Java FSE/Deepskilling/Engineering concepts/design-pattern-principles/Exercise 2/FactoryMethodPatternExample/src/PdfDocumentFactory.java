/**
 * PdfDocumentFactory.java
 *
 * Concrete factory responsible for creating PdfDocument instances.
 * Extends DocumentFactory and overrides the factory method.
 */
public class PdfDocumentFactory extends DocumentFactory {

    /**
     * Factory Method implementation — creates a PdfDocument.
     *
     * @return a new PdfDocument instance
     */
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}
