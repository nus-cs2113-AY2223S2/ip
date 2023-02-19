package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

public class ListCommand extends Command {
    private static final String FIRST_LINE = "Printing Tasks...";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //Check if the TaskList is empty
        if (tasks.isEmpty()) {
            ui.printEmptyList();
            return;
        }
        ui.printTaskList(tasks.getTaskList(), FIRST_LINE);
    }
}
