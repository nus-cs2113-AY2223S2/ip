package max.data;

/**
 * This exception is thrown when there is a logical error dealing with Saving/Loading
 */
public class StorageLoadException extends Exception {
    public StorageLoadException(String errorMessage) {
        super(errorMessage);
    }
}
