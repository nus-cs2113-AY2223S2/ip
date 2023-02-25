package app.exceptions;

public class IncompleteCommandException extends DukeException{
    public IncompleteCommandException(String commandWord) {
        super("ONO! The description of '" + commandWord + "' cannot be empty.");
    }
    public IncompleteCommandException(){
        super("ONO! The description of this command cannot be empty.");
    }
}
