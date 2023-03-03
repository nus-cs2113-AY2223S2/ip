package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class UnMarkCommand extends Command {
    private int taskNo;

    public UnMarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.unmarkTask(taskNo);
        ui.showTaskIncomplete(tasks.getTasks(), taskNo);
        super.execute(tasks, ui, storage);
    }
}
