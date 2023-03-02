package duke.exceptions;

public class IncorrectDeadlineException extends DukeException {

    /**
     * Print an error message when the Deadline input is in an incorrect format
     *
     * @return the error message to be printed
     */
    @Override
    public String printError() {
        return "Duke.Duke.Deadline input task is invalid, input should follow /by";
    }
}
