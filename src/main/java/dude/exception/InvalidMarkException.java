package dude.exception;


public class InvalidMarkException extends DudeException {
    @Override
    public String getMessage() {
        return "Please input a valid mark command e.g.(mark (valid_task_number))";
    }

}
