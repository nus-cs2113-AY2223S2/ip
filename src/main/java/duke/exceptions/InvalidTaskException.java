package duke.exceptions;

import static duke.constants.Constants.LINEBREAK;

public class InvalidTaskException extends Exception {

    /**
     * Returns the error message for invalid task.
     *
     * @return Error message for invalid task.
     */
    public String getMessage() {
        return "Please enter a valid command!\n" + LINEBREAK;
    }

}
