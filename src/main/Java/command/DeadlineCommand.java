package command;

import task.Deadline;
import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(deadline);
        ui.showAdded(tasks, deadline);
        return tasks;
    }
}
