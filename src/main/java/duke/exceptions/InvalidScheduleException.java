package duke.exceptions;

/**
 * Creates new error where invalid start and end date is given
 */
public class InvalidScheduleException extends DukeException {

    /**
     * Error where user has entered a due date that is before start date
     */
    public InvalidScheduleException() {
        super("/by date cannot be before /from date!");
    }
}
