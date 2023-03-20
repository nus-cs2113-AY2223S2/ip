package elzi.command;

import elzi.ElziException;
import elzi.TaskList;
import elzi.Ui;

/**
 * @author : Steven A. O. Waskito
 * Class to find keyword in the list of tasks
 **/
public class FindCommand extends Command {
    private String keyword;

    /**
     * Sets the keyword
     * @param keyword The keyword to search for
     * @throws ElziException if keyword is blank
     */
    public FindCommand(String keyword) throws ElziException {
        if (keyword.isBlank()) {
            throw new ElziException("keyword can't be empty");
        }
        this.keyword = keyword;
    }

    /**
     * Find tasks that contain keywords in the taskList
     * @param taskList taskList ArrayList that stores tasks
     */
    @Override
    public boolean execute(TaskList taskList) {
        TaskList searchList = taskList.search(keyword);
        Ui.printList(searchList, "N");
        return false;
    }
}
