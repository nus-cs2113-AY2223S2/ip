package io.github.haoyangw.rica.storage;

/**
 * Marks an Object as capable of having its state stored in a text file in Rica's
 *   own data format. Implementations must provide a serailizeObject() method that
 *   returns a String representation of the Object's state for storage in a text file.
 */
public interface Serializable {
    public String serializeObject();
}
