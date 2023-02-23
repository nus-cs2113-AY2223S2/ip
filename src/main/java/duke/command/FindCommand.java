package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidDateTime;
import duke.task.TaskList;

/**
 * Find Command class that shortlists Tasks that contain a given keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Initialises the class with the given keyword to shortlist for.
     *
     * @param keyword User input of the keyword from the CLI
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Shortlists and prints Tasks from the TaskList that contain the given keyword.
     *
     * @param tasks The existing TaskList
     * @param ui Prints shortlisted Tasks to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFoundList(tasks.findTasks(keyword));
    }

}
