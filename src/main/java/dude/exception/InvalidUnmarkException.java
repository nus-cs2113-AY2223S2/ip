package dude.exception;

public class InvalidUnmarkException extends DudeException{
    @Override
    public String getMessage(){
        return "Please input a valid Unmark command e.g.(unmark (valid_task_number))";
    }
}
