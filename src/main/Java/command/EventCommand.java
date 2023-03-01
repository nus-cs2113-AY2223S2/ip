package command;

import task.Event;
import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(event);
        ui.showAdded(tasks, event);
        return tasks;
    }
}
