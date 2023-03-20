package duke.command;

import duke.DukeException;
import duke.TaskList;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class ExitCommand extends Command{
    public ExitCommand() {
    }

    @Override
    public boolean execute(TaskList taskList) throws DukeException {
        return true;
    }
}
