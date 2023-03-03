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
    private final TaskEnum taskType;
    private static final String MESSAGE_HEADER = "Invalid input format!" + System.lineSeparator()
            + "Use the following format to create a new task:" + System.lineSeparator();

    /**
     * Class constructor for the task the user is trying to create.
     *
     * @param taskType TaskEnum corresponding to the task type
     */
    public InvalidTaskFormatException(TaskEnum taskType) {
        this.taskType = taskType;
    }

    /**
     * Get the input format of the task the user is attempting to create.
     *
     * @return The error message
     */
    @Override
    public String getMessage() {
        String errorMessage = MESSAGE_HEADER;
        switch (taskType) {
        case TODO:
            errorMessage += FORMAT_TODO;
            break;
        case DEADLINE:
            errorMessage += FORMAT_DEADLINE;
            break;
        case EVENT:
            errorMessage += FORMAT_EVENT;
            break;
        }
        return errorMessage;
    }
}
