package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class DeleteCommand extends Command {
    private int toDeleteIndex;

    public DeleteCommand(int toDeleteIndex) {
        this.toDeleteIndex = toDeleteIndex;
    }

    public boolean execute(TaskList taskList) throws DukeException {
        taskList.removeTask(toDeleteIndex);
        return false;
    }
}
