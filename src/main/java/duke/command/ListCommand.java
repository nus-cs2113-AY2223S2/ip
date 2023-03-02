package duke.command;

import duke.ui.Ui;

/**
 * <code>ListCommand</code> object represents a command that executes
 * the display of all tasks in the TaskList
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    public void execute() {
        Ui.displayList();
    }
}
