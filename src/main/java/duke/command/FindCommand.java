package duke.command;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import static duke.ui.Ui.printBorder;

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
        int counter = 0;
        printBorder();
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : tasks.getTasks()){
            counter++;
            if(t.name.contains(keyword)){
                System.out.println(counter + ". " + t);
            }
        } 
        printBorder();
    }

}
