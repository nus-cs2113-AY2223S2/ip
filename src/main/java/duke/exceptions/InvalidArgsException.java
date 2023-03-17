package duke.exceptions;

import static duke.constants.Constants.LINEBREAK;

public class InvalidArgsException extends Exception {

    public String getMessage() {

        /**
         * Returns the error message when the user tries to perform operations with an incorrect number of commands.
         *
         * @return Error message for incorrect arg number.
         */
        return "Invalid Command. Please try again.\n" + LINEBREAK;
    }
}
