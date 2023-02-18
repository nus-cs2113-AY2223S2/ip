package duke.exceptions;

public class IncorrectEventException extends DukeException {
    @Override
    public String printError() {
        return "Duke.Event input task is invalid, input should follow (/from) and (/to)";
    }
}
