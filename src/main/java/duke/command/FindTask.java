package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class FindTask extends Command {

    private String query;

    public FindTask(String userInput) {
        this.query = userInput.replaceFirst(" ", "");
    }

    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.findQueryTasks(tasks, query);
    }
}

