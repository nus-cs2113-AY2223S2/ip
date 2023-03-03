package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String deadlineTaskName;
    private String by;

    public AddDeadlineCommand(String deadlineTaskName, String by) {
        this.deadlineTaskName = deadlineTaskName;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addDeadlineTask(deadlineTaskName, by);
        ui.showTaskAdded(tasks.getTasks());
        super.execute(tasks, ui, storage);
    }
}
