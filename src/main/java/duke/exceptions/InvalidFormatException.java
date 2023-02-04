package duke.exceptions;
public class InvalidFormatException extends DukeException {
    private static final String exceptionMessage = "Wrong format! Please try again.";

    public InvalidFormatException(String... missing) {
        super("Wrong format! Please include " + missing);
    }

    public InvalidFormatException() {
        super(exceptionMessage);
    }
}
