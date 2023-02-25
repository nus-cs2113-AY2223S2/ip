package app.exceptions;

public class InvalidCommandException extends DukeException{

    public InvalidCommandException() {
        super("ONO! Please enter a valid command.");
    }
}
