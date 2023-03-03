package duke.exception;

public abstract class DukeException extends Exception{
    DukeException(String message){
        super(message);
    }
}
