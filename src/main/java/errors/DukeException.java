package errors;

/**
 * This is an error class that throws errors related to incorrect command input
 * */
public class DukeException extends Exception {
    private static final String INCORRECT_COMMAND = "Pikapi is unable to find this command,"
            + " please specify either todo, deadline, event, list, mark or unmark" + "\n";

    public DukeException() {
        super(INCORRECT_COMMAND);
    }

    public DukeException(String message) {
        super(message);
    }

}
