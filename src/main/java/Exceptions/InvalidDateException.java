package Exceptions;

/**
 * Called when user inputted date is unable to be parsed successfully
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructor for printing invalid date error message
     * @param date Invalid date
     */
    public InvalidDateException(String date) {
        super("Apologies, I do not understand this date: " + 
                date + 
                "!\nPlease format your dates as yyyy-MM-dd [HH:mm]"
        );
    }
}
