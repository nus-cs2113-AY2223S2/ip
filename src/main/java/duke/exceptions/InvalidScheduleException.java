package duke.exceptions;

public class InvalidScheduleException extends DukeException {
    public InvalidScheduleException() {
        super("/by date cannot be before /from date!");
        ;
    }
}
