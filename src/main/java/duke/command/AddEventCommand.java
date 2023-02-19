package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

public class AddEventCommand extends Command{

    protected String description;
    protected String from;
    protected String to;
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Event(description, from, to);
        tasks.addTaskToTaskList(currTask);
        Ui.addSpecialTaskMessage(tasks);
        try {
            database.saveAddTask(currTask.getTaskString());
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }
}
