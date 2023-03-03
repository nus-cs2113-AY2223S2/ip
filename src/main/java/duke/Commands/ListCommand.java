package duke.Commands;

import static duke.Duke.LINE_SPACING;

/*
 * Rerepsents a ListCommand that is sued to display all tasks
 * inherits from Command
 *
 */
public class ListCommand extends Command {
    /**
     * The keyword associated with the ListCommand.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the ListCommand by displaying all tasks in the task list.
     */
    public void cmd() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTask(i));
        }
    }

}
