public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public static class IncompleteInputException extends DukeException {
        public IncompleteInputException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends DukeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }

}
