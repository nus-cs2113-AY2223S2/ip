package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A <code>MarkTaskCommand</code> object takes care of the marking/un-marking of specified tasks.
 */
public class MarkTaskCommand extends Command {
    private static Ui ui;
    private String action;
    private int itemNumber;

    public MarkTaskCommand (String line, TaskList taskList) throws IndexOutOfBoundsException {
        try {
            String[] words = line.split(" ");
            itemNumber = Integer.parseInt(words[1]);
            if (line.startsWith("mark")) {
                action = "mark";
            } else if (line.startsWith("unmark")) {
                action = "unmark";
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printError();
        }

    }

    /**
     * Marks/unmarks task at the specified position.
     *
     * @param taskList List to store all tasks.
     */
    @Override
    public void run(TaskList taskList)  {
        if (action.equals("mark")) {
            taskList.lists.get(itemNumber - 1).markAsDone();
        } else if (action.equals("unmark")) {
            taskList.lists.get(itemNumber - 1).markAsUndone();
        }
        Storage.saveDataFromInput(taskList.lists);
    }
}
