package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Messages;
import duke.ui.Ui;

/**
 * A command for finding tasks with a specific string in their description.
 */
public class FindTasksCommand extends Command {
    private String filter;

    /**
     * Constructs a command that will display tasks containing the specified string when run.
     *
     * @param args Array that should contain the search string at index 1.
     * @throws DukeException If no search string is given.
     */
    FindTasksCommand(String[] args) throws DukeException {
        if (args.length < 2) {
            throw new DukeException(Errors.MISSING_FILTER.MESSAGE);
        }

        this.filter = args[1];
    }

    /**
     * Displays a task list filtered based on the search string specified in the constructor.
     *
     * @param taskList The task list that will be searched.
     */
    @Override
    public void run(TaskList taskList) {
        if (taskList.size() == 0) {
            Ui.print(Messages.EMPTY_LIST.MESSAGE);
        } else {
            String taskListString = taskList.toString(filter);
            if (taskListString.isEmpty()) {
                Ui.print(Messages.EMPTY_FIND.MESSAGE);
                return;
            }
            Ui.print(Messages.FIND_TASKS.MESSAGE, taskListString);
        }
    }
}
