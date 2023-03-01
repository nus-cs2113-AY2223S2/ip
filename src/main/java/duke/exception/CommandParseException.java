package duke.exception;

/**
 * The CommandParseException class handles
 * CommandParseException exceptions
 */
public class CommandParseException extends DukeException {
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    public CommandParseException(){
        super();
    }

    /**
     * This method prints the error message
     */
    public static void errorMessage () {
        System.out.println(DIVIDER);
        System.out.println(SPACER + "☹ OOPS!!! Please enter a proper keyword.");
        System.out.println(DIVIDER);
    }


}

