package chronos.exceptions;

/**
 * This class defines custom exceptions for the Chronos application.
 */
public class ChronosExceptions extends Exception {
    /**
     * Constructor for the ChronosExceptions class.
     *
     * @param message The error message associated with the exception.
     */
    public ChronosExceptions(String message) {
        super(message);
    }
    /**
     * Custom exception for handling invalid user input in the Chronos application.
     */
    public static class InvalidInputException extends ChronosExceptions {
        /**
         * Constructor for the InvalidInputException class.
         *
         * @param message The error message associated with the exception.
         */
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
