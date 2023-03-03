package inu.commands;

import inu.commons.Messages;
import inu.commons.Util;
import inu.task.TaskList;
import java.time.LocalDate;

/**
 * List all the tasks found on a specific date in the task list to the user.
 */
public class ListWithDateCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final LocalDate date;

    /**
     * Constructor.
     *
     * @param date the date to query.
     */
    public ListWithDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(Messages.MESSAGE_LIST_HEADER_WITH_DATE + Util.convertDateToString(date) + "\n"
                + taskList.printList(taskList.filterDate(date)));
    }
}
