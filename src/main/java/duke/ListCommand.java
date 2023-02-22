package duke;

public class ListCommand extends Command {
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getAllTasks());
    }
}
