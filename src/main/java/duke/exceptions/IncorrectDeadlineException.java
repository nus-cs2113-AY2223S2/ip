package duke.exceptions;

public class IncorrectDeadlineException extends DukeException {
    @Override
    public String printError() {
        return "Duke.Duke.Deadline input task is invalid, input should follow /by";
    }
}
