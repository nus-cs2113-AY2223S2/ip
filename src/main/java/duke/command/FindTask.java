package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Finds if tasks in taskList contains a query string
 */
public class FindTask extends Command {

    private String query;

    /**
     * Constructor to process and set query string
     *
     * @param userInput raw query string
     */
    public FindTask(String userInput) {
        this.query = userInput.replaceFirst(" ", "");
    }

    /**
     * Finds task(s) based on whether task description contains query string
     *
     * @param tasks   TaskList of tasks currently stored
     * @param storage Handler to read write to json
     * @param ui      Handler to print text to user
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.findQueryTasks(tasks, query);
    }
}

