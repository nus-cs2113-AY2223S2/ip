package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * FindCommand class that inherits from Command class that handles the user input of find whereby it searches keywords
 * inside TaskList to find similar types of tasks that description contains the keywords
 */
public class FindCommand extends Command {
    /**
     * The keyword to filter by
     */
    protected String keyword;

    /**
     * Constructor of the FindCommand object that initialises the target keyword the user wants to filter by
     *
     * @param keyword the word the user wants to filter by and search up similarities
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand where it prints all the tasks that are compatible with the keyword inputted by user
     *
     * @param tasks    the TaskList that is being referred to
     * @param database the Storage that is being referred to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.findTaskByKeywordOpeningMessage();
        tasks.printTasksByKeyword(keyword);
        Ui.printLine();
        System.out.println(); // add one line of separation
    }

}
