package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds tasks in task list that contains keyword[s] in the task name
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String OUTPUT_MESSAGE = " Here are the matching tasks in your list:";

    public static final String NO_SIMILAR_TASK_MESSAGE =
            " There are no similar task in your list that matches the keyword[s] given";

    public static final String MESSAGE_USAGE = " find: find task[s] that contains keyword[s]"
            + Ui.NEW_LINE + "  Parameters: keyword"
            + Ui.NEW_LINE + "  Example: find bread";

    public String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        String output = Ui.SEGMENT_LINE;
        TaskList matchingTasks = taskList.findTasks(keyword);
        if (matchingTasks.getTaskCount() == 0) {
            output = String.join(Ui.NEW_LINE, output, NO_SIMILAR_TASK_MESSAGE);
        } else {
            output = String.join(Ui.NEW_LINE, output, OUTPUT_MESSAGE);
            output = getFilteredTasksInformation(output, matchingTasks);
        }
        return new CommandResult(output);
    }
}
