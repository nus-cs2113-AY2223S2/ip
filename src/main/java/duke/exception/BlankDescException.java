package duke.exception;

/**
 * The BlankDescException class handles
 * blank description errors when creating a task
 */
public class BlankDescException extends DukeException {
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    public BlankDescException(){
        super();
    }

    /**
     * This method prints the error message of a specific type
     * @param type Type of Task
     */
    public static void errorMessage (String type) {
        System.out.println(DIVIDER);
        System.out.println(SPACER + "☹ OOPS!!! The description of a "+type+" cannot be empty.");
        System.out.println(DIVIDER);
    }


}

