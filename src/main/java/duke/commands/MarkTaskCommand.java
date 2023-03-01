package duke.commands;

import duke.exception.InvalidIndexException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command generated for marking a task to be completed.
 */
public class MarkTaskCommand extends Command{
    private int index;
    private boolean isDone;

    /**
     * Constructs a command to set the completion status of a task to be done.
     *
     * @param input The index of a task in the task list to be marked as done.
     * @param taskList The task list to be referenced from for the marking of the task.
     *                 Checks for whether the index is out of bounds.
     * @throws InvalidIndexException The exception thrown when user enters an invalid index.
     */
    public MarkTaskCommand (String input, TaskList taskList) throws InvalidIndexException {
        index = Integer.parseInt(input) - 1;
        if (index >= taskList.listCount()) {
            throw new InvalidIndexException();
        }
        isDone = true;
    }

    /**
     * Sets the completion status of the task at the specified index to be true.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.setDone(index, isDone);
        Ui.markDoneMessage(taskList, index);
    }
}
