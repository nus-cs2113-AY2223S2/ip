package elzi.command;

import elzi.ElziException;
import elzi.TaskList;
import elzi.Ui;

/**
 * @author : Steven A. O. Waskito
 * Class to mark or unmark specific task
 **/
public class SetAsDoneCommand extends Command {
    private boolean isDone;
    private int index;

    /**
     * Set the index and mark or unmark command
     * @param index index of the task in taskList
     * @param isDone mark or unmark command
     */
    public SetAsDoneCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }
    /**
     * Mark or unmark the specific task
     * @param taskList taskList ArrayList that stores tasks
     * @throws ElziException if index is out of bounds or not an integer
     */
    public boolean execute(TaskList taskList) throws ElziException {
        if (isDone) {
            taskList.setTaskAsDone(index);
            Ui.printSetAsDone(taskList.getIndex(index));
        } else {
            taskList.setTaskAsNotDone(index);
            Ui.printSetAsNotDone(taskList.getIndex(index));
        }
        Ui.printList(taskList, "N");
        return false;
    }
}
