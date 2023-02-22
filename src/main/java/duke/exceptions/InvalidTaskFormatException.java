package duke.exceptions;

import duke.tasks.TaskEnum;

public class InvalidTaskFormatException extends Exception {
    private static final String MESSAGE_HEADER = "Invalid input format!\n"
            + "Use the following format to create a new task:\n";
    private static final String FORMAT_TODO = "Todo - todo <task details>\n";
    private static final String FORMAT_DEADLINE = "Deadline - deadline <task details> /by <date details>\n";
    private static final String FORMAT_EVENT = "Event - event <task details> /from <date details> /to <date details>\n";
    private final TaskEnum id;

    public InvalidTaskFormatException(TaskEnum t) {
        this.id = t;
    }

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
