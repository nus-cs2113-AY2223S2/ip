package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

/**
 * Command for deleting a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Constructs a command that will delete the task at the given index from the task list.
     *
     * @param command Array that should contain the index of the task to be deleted at index 1.
     * @param taskList The task list that the task is being deleted from,
     *                 needed for checking if the given index is a valid task index.
     * @throws DukeException If no task index is provided, or if the provided index is not a number,
     *                       or if the provided index is not from 1 to the size of the task list.
     */
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

    /**
     * Deletes the task with the index specified in the constructor from the task list.
     *
     * @param taskList The task list that the command is executed on.
     */
    public void run(TaskList taskList) {
        String taskString = taskList.deleteTask(index);
        Ui.printDeleteTaskMessage(taskString, taskList);
    }
}
