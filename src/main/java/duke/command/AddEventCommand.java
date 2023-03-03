package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddEventCommand extends Command {
    private String eventTaskName;
    private String from;
    private String to;

    public AddEventCommand (String eventTaskName, String from, String to) {
        this.eventTaskName = eventTaskName;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addEventTask(eventTaskName, from, to);
        ui.showTaskAdded(tasks.getTasks());
        super.execute(tasks, ui, storage);
    }
}
