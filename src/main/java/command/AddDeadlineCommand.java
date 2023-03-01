package command;

import task.Deadline;
import components.TaskList;
import components.Ui;
import components.Storage;

public class AddDeadlineCommand extends Command{
    public AddDeadlineCommand(String[] commandFields) {
        super(commandFields);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.tasks.add(new Deadline(commandFields[0], commandFields[1]));
        ui.taskAdded(tasks.tasks);
        storage.writeToFile(tasks.tasks, storage.filePath);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
