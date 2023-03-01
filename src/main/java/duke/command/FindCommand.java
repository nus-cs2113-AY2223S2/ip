package duke.command;

import duke.exceptions.InvalidSearchWordException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the find feature in Duke. Task objects which description contains
 * keyword entered by the user will be listed out.
 * e.g., <code>find tuition</code>
 */
public class FindCommand {

    /**
     * Finds task from the saved task list.
     *
     * @param tasks   Task list containing all saved task
     * @param keyword User entered search word
     */

    public static void findTask(TaskList tasks, String keyword){
        try {
            if(keyword == null){
                throw new InvalidSearchWordException();
            }
            int counter = 0;
            Ui.printBorder();
            System.out.println("Here are the matching tasks in your list:");
            for (Task t : tasks.getTasks()) {
                counter++;
                if (t.name.contains(keyword)) {
                    System.out.println(counter + ". " + t);
                }
            }
            Ui.printBorder();
        } catch (InvalidSearchWordException e){
            InvalidSearchWordException.printErrorMessage();
            Ui.printBorder();
        }
    }
}
