/**
 * ExcelDocumentFactory.java
 *
 * Concrete factory responsible for creating ExcelDocument instances.
 * Extends DocumentFactory and overrides the factory method.
 */
public class ExcelDocumentFactory extends DocumentFactory {

    /**
     * Factory Method implementation — creates an ExcelDocument.
     *
     * @return a new ExcelDocument instance
     */
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
