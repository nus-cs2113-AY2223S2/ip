package duke.command;

import duke.task.Deadline;
import duke.ui.Ui;

/**
 * Adds a deadline task
 */
public class DeadlineCommand extends AddCommand {

    public static final String MISSING_KEYWORD_MESSAGE =
            " Invalid input! Valid input format: \"deadline <task name> /by <date>\"";

    public static final String INSUFFICIENT_FIELD_MESSAGE = " Invalid input! Please provide enough arguments! "
            + Ui.NEW_LINE + " Valid input format: \"deadline <task name> /by <date>\"";

    public String deadline;

    public DeadlineCommand(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        taskList.addTask(new Deadline(taskName, deadline));
        String output = giveAddMessage();
        return new CommandResult(output);
    }
}
