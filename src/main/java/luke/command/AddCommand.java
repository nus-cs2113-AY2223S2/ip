package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.AddTaskException;

import java.io.IOException;

public class AddCommand extends Command {
    private String taskType;
    private String taskName;
    private String taskDates;
    public AddCommand(String taskType, String taskName, String taskDates) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskDates = taskDates;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isAdded = tasks.addTask(taskType, taskName, taskDates);

        if (!isAdded) {
            new InvalidCommand().execute(tasks, ui, storage);
            return;
        }

        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            storage.handleSaveError();
        }
        ui.printAddTask(taskName);
    }
}
