package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * A <code>SearchCommand</code> object takes care of the searching and returning of tasks related to the
 * search term provided.
 */

public class SearchCommand extends Command {
    private String searchTerm;
    private ArrayList<Task> searchResults = new ArrayList<>();
    public SearchCommand (String line) throws IndexOutOfBoundsException {
        try {
            String[] words = line.split(" ", 2);
            searchTerm = words[1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You must include a search term for your search.");
        }
    }

    /**
     * Searches through list given and checks for those with descriptions matching search terms.
     * Appends all relevant search results to searchResults.
     *
     * @param taskList List to store all tasks.
     */
    @Override
    public void run(TaskList taskList)  {
        for (Task item : taskList.lists) {
            if (item.description.contains(searchTerm)) {
                searchResults.add(item);
            }
        }
        Ui.printSearchResults(searchResults);
    }
}
