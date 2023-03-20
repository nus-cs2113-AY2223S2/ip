package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class SetAsDoneCommand extends Command {
    private boolean isDone;
    private int index;

    public SetAsDoneCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    public boolean execute(TaskList taskList) throws DukeException {
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
