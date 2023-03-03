package duke.exceptions;

import duke.parser.datetime.DateTimeParser;
import duke.tasks.TaskEnum;

/**
 * Exception when the input task command does not follow the required format.
 */
public class InvalidTaskFormatException extends Exception {
    private static final String DATE_FORMAT = DateTimeParser.getFormat();
    private static final String FORMAT_DEADLINE = "Deadline - deadline <task details> "
            + "/by <" + DATE_FORMAT + ">"
            + System.lineSeparator();
    private static final String FORMAT_EVENT = "Event - event <task details>"
            + "/from <" + DATE_FORMAT + "> "
            + "/to <" + DATE_FORMAT + ">"
            + System.lineSeparator();
    private static final String FORMAT_TODO = "Todo - todo <task details>" + System.lineSeparator();
    private final TaskEnum id;
    private static final String MESSAGE_HEADER = "Invalid input format!" + System.lineSeparator()
            + "Use the following format to create a new task:" + System.lineSeparator();

    /**
     * Class constructor for the task the user is trying to create.
     *
     * @param t TaskEnum corresponding to the task type
     */
    public InvalidTaskFormatException(TaskEnum t) {
        this.id = t;
    }

    /**
     * Get the input format of the task the user is attempting to create.
     *
     * @return The error message
     */
    @Override
    public String getMessage() {
        String msg = MESSAGE_HEADER;
        switch (id) {
        case TODO:
            msg += FORMAT_TODO;
            break;
        case DEADLINE:
            msg += FORMAT_DEADLINE;
            break;
        case EVENT:
            msg += FORMAT_EVENT;
            break;
        }
        return msg;
    }
}
