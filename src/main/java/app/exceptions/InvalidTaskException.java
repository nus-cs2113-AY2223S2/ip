package app.exceptions;

public class InvalidTaskException extends DukeException{

    public InvalidTaskException() {
        super("ONO! Please enter a valid task index.");
    }
}
