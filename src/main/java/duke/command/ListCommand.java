package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class ListCommand extends Command{
    public ListCommand() {
    }

    public boolean execute(TaskList taskList) {
        Ui.printList(taskList);
        return false;
    }
}
