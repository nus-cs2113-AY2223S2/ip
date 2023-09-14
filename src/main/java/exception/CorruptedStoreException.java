package exception;

/**
 * This exception is thrown to indicate that a data store has become corrupted
 * and is unable to function properly.
 */
public class CorruptedStoreException extends Exception {
    public CorruptedStoreException() {
        super();
    }
}
