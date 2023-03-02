package duke.exceptions;

public class IncorrectEventException extends DukeException {

    /**
     * Print an error message when the Event input is in an incorrect format
     *
     * @return the error message to be printed
     */
    @Override
    public String printError() {
        return "Duke.Event input task is invalid, input should follow (/from) and (/to)";
    }
}
