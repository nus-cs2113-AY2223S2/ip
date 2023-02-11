package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand(String[] command, TaskList taskList) throws DukeException {
        if (command.length < 2) {
            throw new DukeException(Errors.MISSING_INDEX.MESSAGE);
        }

        try {
            index = Integer.parseInt(command[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException(Errors.INVALID_INDEX.MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(Errors.INVALID_INDEX.MESSAGE);
        }
    }

    public void run(TaskList taskList) {
        String taskString = taskList.deleteTask(index);
        Ui.printDeleteTaskMessage(taskString, taskList);
    }
}
