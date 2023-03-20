package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) throws DukeException {
        if (keyword.isBlank()){
            throw new DukeException("keyword can't be empty");
        }
        this.keyword = keyword;
    }

    @Override
    public boolean execute(TaskList taskList) throws DukeException {
        TaskList searchList = taskList.search(keyword);
        Ui.printList(searchList, "N");
        return false;
    }
}
