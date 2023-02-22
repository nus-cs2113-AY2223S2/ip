package dude.exception;


public class InvalidDeleteException extends DudeException {
    @Override
    public String getMessage(){
        return "Please input a valid delete command e.g.(delete (valid_task_number))";
    }
}
