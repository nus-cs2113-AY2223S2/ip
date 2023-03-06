package duke.command;

import duke.task.Event;
import duke.ui.Ui;

/**
 * Adds an event task
 */
public class EventCommand extends AddCommand {
    public static final String INVALID_FORMAT_MESSAGE =
            " Invalid input! Valid input format: \"event <task name> /from <date> /to <date>\"";
    public static final String INSUFFICIENT_FIELD_MESSAGE = " Invalid input! Please provide enough arguments! "
            + Ui.NEW_LINE + " Valid input format: \"event <task name> /from <date> /to <date>\"";
    public String startDate;
    public String endDate;

    public EventCommand(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        taskList.addTask(new Event(taskName, startDate, endDate));
        String output = giveAddMessage();
        return new CommandResult(output);
    }
}
