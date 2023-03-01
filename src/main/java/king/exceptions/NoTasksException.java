package king.exceptions;

/**
 * An exception representing an empty filtered list when filtered by keyword
 */
public class NoTasksException extends KingException {
    public NoTasksException(String filter) {
        super(String.format("My utmost apologies sir, nay tasks fit thy keyword '%s'", filter));
    }
}
