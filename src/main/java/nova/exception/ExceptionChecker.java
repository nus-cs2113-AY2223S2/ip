package nova.exception;

/**
 * An exception for the issue when one of the task inputs is missing
 */
public class ExceptionChecker {
    public static void checkEmptyString(String string) throws EmptyInputsException {
        if (string.isEmpty()) {
            throw new EmptyInputsException();
        }
    }
}
