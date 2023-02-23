package duke.exceptions;

import static duke.constants.Constants.LINEBREAK;

public class DeadlineDateException extends Exception{

        /**
        * Returns the error message for invalid deadline date.
        *
        * @return Error message for invalid date.
        */
        public String getMessage() {
            return "Your dates are invalid. Please ensure your deadline has not already passed.\n" + LINEBREAK;
        }
}
