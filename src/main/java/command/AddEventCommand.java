package command;

import task.Event;
import components.TaskList;
import components.Ui;
import components.Storage;


public class AddEventCommand extends Command {
    public AddEventCommand(String[] commandFields) {
        super(commandFields);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.tasks.add(new Event(commandFields[0], commandFields[1], commandFields[2]));
        ui.taskAdded(tasks.tasks);
        storage.writeToFile(tasks.tasks, storage.filePath);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
