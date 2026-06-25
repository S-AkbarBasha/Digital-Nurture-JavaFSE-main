/**
 * DocumentFactory.java
 *
 * Abstract Factory class implementing the Factory Method Pattern.
 *
 * The Factory Method (createDocument) is declared here and left
 * for concrete subclasses to implement, allowing each subclass
 * to decide which Document type to instantiate.
 */
public abstract class DocumentFactory {

    /**
     * Factory Method — subclasses override this to create
     * the specific Document type they are responsible for.
     *
     * @return a new Document instance
     */
    public abstract Document createDocument();

    /**
     * Template method: creates a document and immediately
     * performs a standard open-save-close workflow.
     * Demonstrates that the factory also controls document lifecycle.
     */
    public void openAndSaveDocument() {
        Document doc = createDocument();
        System.out.println("  >> Created: " + doc.getDocumentType());
        doc.open();
        doc.save();
        doc.close();
    }
}
