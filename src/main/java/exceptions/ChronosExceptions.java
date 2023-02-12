package exceptions;

public class ChronosExceptions extends Exception {
    public ChronosExceptions(String message) {
        super(message);
    }

    public static class InvalidInputException extends ChronosExceptions {
        public InvalidInputException(String message) {
            super(message);
        }
    }


}
