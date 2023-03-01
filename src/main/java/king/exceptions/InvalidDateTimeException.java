package king.exceptions;

import king.parser.DateTimeParser;

/**
 * An exception representing an input string which cannot
 * be parsed into a valid DateTime object
 */
public class InvalidDateTimeException extends KingException {
    private static final String MESSAGE = "Thy date and time art in the wrong format! Please useth this: \n"
            + "\t\t" + DateTimeParser.getFormat() + " in 24-hour format"; 

    public InvalidDateTimeException() {
        super(MESSAGE);
    }
}
