package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class ListCommand extends Command{
    private String type;
    public ListCommand(String type) {
        this.type = type;
    }

    public boolean execute(TaskList taskList) {
        Ui.printList(taskList, type);
        return false;
    }
}
