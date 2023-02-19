package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;

public class AddDeadlineCommand extends Command {

    protected String[] stringSplit;

    public AddDeadlineCommand(String[] stringSplit) {
        this.stringSplit = stringSplit;
    }

    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Deadline(stringSplit[0], stringSplit[1]);
        tasks.addTaskToTaskList(currTask);
        Ui.addSpecialTaskMessage(tasks);
        try {
            database.saveAddTask(currTask.getTaskString());
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }
}
