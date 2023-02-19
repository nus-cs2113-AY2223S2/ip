package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends Command{

    protected int deleteIndex;

    public DeleteTaskCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.deleteTaskMessage(deleteIndex, tasks);
        tasks.deleteTaskFromTaskList(deleteIndex);
        try {
            database.updateDatabaseTask();
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }
}
