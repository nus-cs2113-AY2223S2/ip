package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand (int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showTaskDeleted(tasks.getTasks(), taskNo);
        tasks.deleteTask(taskNo);
        super.execute(tasks, ui, storage);
    }
}
