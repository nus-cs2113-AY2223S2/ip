package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.UI;

/**
 * Command class for finding tasks containing a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object.
     * @param keyword The word to be searched for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks in the task list that contains the keyword to be searched for.
     * @param tasks The task list that the user modifies
     * @param storage Updates when task list is modified
     * @param ui Prints tasks that contain the keyword to be searched for.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        ui.printFoundTasks(tasks.findTasks(keyword));
        ui.printSeparator();
    }
}
