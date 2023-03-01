package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

/**
 * Command for changing a task's completion status.
 */
public class MarkTaskCommand extends Command {
    private int index;
    private boolean isDone;

    /**
     * Constructs a command that will set the completion
     * status of the task at the given index of the task list.
     *
     * @param command Array that should contain the index of the task to be marked or unmarked at index 1.
     * @param taskList The task list that the task being marked or unmarked is from,
     *                 needed for checking if the given index is a valid task index.
     * @throws DukeException If no task index is provided, or if the provided index is not a number,
     *                       or if the provided index is not from 1 to the size of the task list.
     */
    public MarkTaskCommand(String[] command, TaskList taskList) throws DukeException {
        if (command.length < 2) {
            throw new DukeException(Errors.MISSING_INDEX.MESSAGE);
        }

        try {
            isDone = command[0].equals("mark");
            index = Integer.parseInt(command[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException(Errors.INVALID_INDEX.MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(Errors.INVALID_INDEX.MESSAGE);
        }
    }

    /**
     * Sets the completion status of the task at the index specified
     * in the constructor to either true or false depending on whether
     * a "mark" or "unmark" command was provided in the constructor.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.setDone(index, isDone);
        Ui.printMarkTaskMessage(taskString, isDone);
    }
}
