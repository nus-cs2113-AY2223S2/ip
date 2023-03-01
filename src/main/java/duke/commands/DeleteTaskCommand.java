package duke.commands;

import duke.exception.InvalidIndexException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command for deleting a task from the task list.
 */
public class DeleteTaskCommand extends Command{
    private int index;

    /**
     * Constructs a command that will delete the task at the given index from the task list.
     *
     * @param input The index of task to be deleted from the task list.
     * @param taskList The task list to be referenced from for the deleting of the task at the index.
     *                 Used for identifying if the index is out of bounds.
     * @throws InvalidIndexException The exception thrown when user enters an invalid index.
     */
    public DeleteTaskCommand(String input, TaskList taskList) throws InvalidIndexException {
        index = Integer.parseInt(input) - 1;
        if (index >= taskList.listCount()) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Deletes the task with the index specified in the constructor from the task list.
     *
     * @param taskList The task list that the command is executed on
     */
    @Override
    public void execute(TaskList taskList){
        Ui.deleteMessage();
        System.out.println("  " + taskList.getTask(index).toString());
        taskList.deleteTasks(index);
        Ui.printListMessage(taskList.listCount());
    }
}
