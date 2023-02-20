package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public abstract class AddCommand extends Command {

    public void addTaskToDatabase(Task currTask, Storage database) {
        try {
            database.saveAddTask(currTask.getTaskString());
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }

    public void addTaskToTaskList(TaskList tasks, Task currTask) {
        tasks.addTaskToTaskList(currTask);
        Ui.addSpecialTaskMessage(tasks);
    }
}
