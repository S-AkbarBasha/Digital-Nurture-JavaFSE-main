/**
 * Document.java
 *
 * Abstract interface representing a generic Document.
 * All concrete document types must implement this interface.
 */
public interface Document {

    /**
     * Opens the document.
     */
    void open();

    /**
     * Saves the document.
     */
    void save();

    /**
     * Closes the document.
     */
    void close();

    /**
     * Returns the type/name of the document.
     *
     * @return document type as a String
     */
    String getDocumentType();
}
