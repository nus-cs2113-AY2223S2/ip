package command;

import task.Deadline;
import components.TaskList;
import components.UI;
import components.Storage;

public class AddDeadlineCommand extends Command{
    public AddDeadlineCommand(String[] commandFields) {
        super(commandFields);
    }

    /**
     * Adds and writes task of <code>Deadline</code> to the ArrayList.
     *
     * @param tasks ArrayList of tasks.
     * @param ui Deals with interactions with the user.
     * @param storage Deals with saving and loading tasks in the file.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.tasks.add(new Deadline(commandFields[0], commandFields[1]));
        ui.taskAdded(tasks.tasks);
        storage.writeToFile(tasks.tasks, storage.filePath);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
