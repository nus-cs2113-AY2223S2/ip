package errors;

public class DukeException extends Exception {

    public DukeException() {
        super("Pikapi is unable to find this command, please specify either todo, deadline, event, list, mark or unmark\n");
    }

    public DukeException(String message) {
        super(message);
    }

}
