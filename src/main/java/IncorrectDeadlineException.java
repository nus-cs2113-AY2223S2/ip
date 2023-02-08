public class IncorrectDeadlineException extends DukeException{
    @Override
    public String printError() {
        return "Deadline input task is invalid, input should follow /by";
    }
}
