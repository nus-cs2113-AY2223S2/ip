package duke.exception;

/**
 * The DukeException class handles
 * general Duke exceptions
 */
public class DukeException extends Exception{
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    DukeException(){
        super();
    }

    /**
     * This method prints the error message
     */
    public static void errorMessage () {
            System.out.println(DIVIDER);
            System.out.println(SPACER + "☹ OOPS!!! An error has occured!.");
            System.out.println(DIVIDER);
    }
}
