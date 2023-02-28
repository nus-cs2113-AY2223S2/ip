package duke.exceptions;

import static duke.constants.Constants.LINEBREAK;

public class EventDateException extends Exception {

    /**
     * Returns the error message for invalid event date.
     *
     * @return Error message for invalid date.
     */
    public String getMessage() {
        return "Your dates are invalid. Please ensure your start date and time is before your end date and time.\n"
                + LINEBREAK;
    }
}
