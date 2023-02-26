package app.exceptions;

/**
 * Class to handle the case of incomplete commands entered by user.
 */
public class IncompleteCommandException extends DukeException{

    /**
     * Constructor to output an error message if a command word is entered
     * without any accompanying description.
     * @param commandWord The type of command that was entered without any additional description.
     */
    public IncompleteCommandException(String commandWord) {
        super("ONO! The description of '" + commandWord + "' cannot be empty.");
    }

    /**
     * Constructor to output an error message for any generic command that does
     * not have any accompanying description.
     */
    public IncompleteCommandException(){
        super("ONO! The description of this command cannot be empty.");
    }
}
