package Duke.command;

import Duke.DukeException;
import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;

/**
 * DukeFindCommand is the class that deals with the find command.
 */
public class DukeFindCommand extends DukeCommand {

    private String keyword;

    /**
     * Creates a new DukeFindCommand object.
     * Searches for tasks that contain the keyword.
     * 
     * @param keyword the keyword to be searched
     * @throws DukeException if the keyword is empty
     */
    public DukeFindCommand(String keyword) throws DukeException {
        if (!keyword.isEmpty()) {
            this.keyword = keyword;
        } else {
            throw new DukeException("The keyword cannot be empty.");
        }
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        tasks.findTasksList(keyword, ui);
    }

}
