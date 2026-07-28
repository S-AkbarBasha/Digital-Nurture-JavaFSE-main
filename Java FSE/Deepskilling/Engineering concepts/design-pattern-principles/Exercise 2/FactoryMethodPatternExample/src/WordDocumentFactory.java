/**
 * WordDocumentFactory.java
 *
 * Concrete factory responsible for creating WordDocument instances.
 * Extends DocumentFactory and overrides the factory method.
 */
public class WordDocumentFactory extends DocumentFactory {

    /**
     * Factory Method implementation — creates a WordDocument.
     *
     * @return a new WordDocument instance
     */
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
