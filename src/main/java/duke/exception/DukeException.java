package duke.exception;

/**
 * Custom Duke Exception to Show Encountered Error Messages to the User while performing Duke-Related Tasks
 * */
public class DukeException extends Exception {
    protected static String errorMessage;

    /**
     * Stores the Error Message
     *
     * @param e The Error Message
     * */
    public DukeException(String e) {
        errorMessage = e;
    }

    /**
     * Prints the Error Message
     * */
    public static void showError() {
        System.out.println(errorMessage);
    }
}