package duke.commands;

import duke.exception.EmptyCommandException;
import duke.exception.EmptyIndexException;
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
     * @param cases An array of string that contains the command and the index.
     * @param taskList The task list to be referenced from for the marking of the task.
     *                 Checks for whether the index is out of bounds.
     * @throws InvalidIndexException The exception thrown when user enters an invalid index.
     * @throws EmptyIndexException The exception thrown when user did not input the index.
     */
    public MarkTaskCommand (String[] cases, TaskList taskList) throws InvalidIndexException, EmptyIndexException {
        if (cases.length == 1){
            throw new EmptyIndexException();
        }
        String input = cases[1];
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
