package duke.command;

import duke.ui.Ui;

/** Represents a command that adds task */
public abstract class AddCommand extends Command {

    public static final String COMMAND_TODO = "todo";

    public static final String COMMAND_DEADLINE = "deadline";

    public static final String COMMAND_EVENT = "event";

    public static final String ADD_MESSAGE = " The following task has been added:"
            + Ui.NEW_LINE + "   %s"
            + Ui.NEW_LINE + " The task list now has %d task(s) in total.";

    public static final String TODO_MESSAGE_USAGE = " " + COMMAND_TODO + ": adds a todo task to the task list. "
            + Ui.NEW_LINE + "  Parameters: task name"
            + Ui.NEW_LINE + "  Example: " + COMMAND_TODO + " buy bread";

    public static final String DEADLINE_MESSAGE_USAGE = " " + COMMAND_DEADLINE + ": adds a deadline task to the task list. "
            + Ui.NEW_LINE + "  Parameters: task name, deadline"
            + Ui.NEW_LINE + "  Example: " + COMMAND_DEADLINE + " eat bread /by today";

    public static final String EVENT_MESSAGE_USAGE = " " + COMMAND_EVENT + ": adds an event task to the task list. "
            + Ui.NEW_LINE + "  Parameters: task name, start date, end date"
            + Ui.NEW_LINE + "  Example: " + COMMAND_EVENT + " make bread /from today 3pm /to 5pm";

    public String taskName;

    public AddCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Generates the string to be shown to user when a task is added
     *
     * @return string to be shown to user
     */
    public String giveAddMessage() {
        String output = Ui.SEGMENT_LINE;
        output = String.join(Ui.NEW_LINE, output, AddCommand.ADD_MESSAGE);
        int taskCount = taskList.getTaskCount();
        output = output.replace("%s", taskList.getTaskFullDetails(taskCount - 1));
        output = output.replace("%d", Integer.toString(taskCount));
        return output;
    }
}
