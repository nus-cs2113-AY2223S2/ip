package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

public class FindTasksCommand extends Command {
    private String filter;

    FindTasksCommand(String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException(Errors.MISSING_FILTER.MESSAGE);
        }

        this.filter = command[1];
    }

    @Override
    public void run(TaskList taskList) {
        Ui.printTaskList(taskList, filter);
    }
}
