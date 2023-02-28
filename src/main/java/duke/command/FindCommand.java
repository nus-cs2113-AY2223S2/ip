package duke.command;

import duke.exception.EmptyListError;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Command when dealing with searching using a keyword
 */
public class FindCommand extends Command {

    protected String keyword;
    public FindCommand(String commandType, String keyword) {
        super(commandType);
        this.keyword = keyword;
    }

    /**
     * Executes the find command
     *
     * @param tasks tasklist which contains all the tasks
     * @throws EmptyListError if error occurred due to empty list
     */
    @Override
    public void execute(TaskList tasks) {
        try {
            tasks.findInList(keyword);
        } catch (EmptyListError err){
            System.out.println("There is nothing inside the list");
        }

    }

}