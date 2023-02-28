package duke.exceptions;

import static duke.constants.Constants.LINEBREAK;

public class EmptyListException extends Exception {

    /**
     * Returns the error message when the user tries to perform operations on an empty list.
     *
     * @return Error message for an empty list.
     */
    public String getMessage() {
        return "The list is empty. Please add a task first.\n" + LINEBREAK;
    }

}
