package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    /**
     * Initialises an instance of Command.
     */
    public Command() {

    }

    /**
     * Executes command.
     * Save updated task array list into duke.txt.
     *
     * @param tasks Instance of task list containing task array list.
     * @param ui Instance of ui.
     * @param storage Instance of storage.
     * @throws IOException if there is an error creating ./data/duke.txt or writing task array list to duke.txt.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.writeFile(tasks.getTasks());
    }

    /**
     * Returns false to prevent Duke chatbot from exiting.
     *
     * @return false to prevent Duke chatbot from exiting.
     */
    public boolean isExit() {
        return false;
    }
}
