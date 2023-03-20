package elzi.command;

import elzi.ElziException;
import elzi.TaskList;
import elzi.Ui;

/**
 * @author : Steven A. O. Waskito
 * Class to delete todo, deadline, event tasks
 **/
public class DeleteCommand extends Command {
    private int toDeleteIndex;

    /**
     * Sets the index to delete
     * @param toDeleteIndex index to delete
     */
    public DeleteCommand(int toDeleteIndex) {
        this.toDeleteIndex = toDeleteIndex;
    }

    /**
     * Deletes the task in the taskList
     * @param taskList taskList ArrayList that stores tasks
     * @throws ElziException if index is out of bounds
     */
    public boolean execute(TaskList taskList) throws ElziException {
        Ui.printDeleteTask(taskList.getIndex(toDeleteIndex));
        taskList.removeTask(toDeleteIndex);
        Ui.printTaskLeft(taskList);
        return false;
    }
}
