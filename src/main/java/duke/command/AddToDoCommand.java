package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddToDoCommand extends Command {
    private String toDoTaskName;

    public AddToDoCommand (String toDoTaskName) {
        this.toDoTaskName = toDoTaskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTodoTask(toDoTaskName);
        ui.showTaskAdded(tasks.getTasks());
        super.execute(tasks, ui, storage);
    }
}
