package elzi.command;

import elzi.ElziException;
import elzi.TaskList;
import elzi.Ui;

/**
 * @author : Steven A. O. Waskito
 * Class to list tasks or event or deadline or todo
 **/
public class ListCommand extends Command {
    private String type;

    /**
     * Sets list type
     * @param type type of list (normal, todo, deadline, event)
     */
    public ListCommand(String type) {
        this.type = type;
    }

    /**
     * List the type of list
     * @param taskList taskList ArrayList that stores tasks
     */
    public boolean execute(TaskList taskList) {
        Ui.printList(taskList, type);
        return false;
    }
}
