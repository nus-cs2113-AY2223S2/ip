package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String toFind;
    private static final String FIRST_LINE = "Printing matching tasks...";

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printEmptyList();
            return;
        }
        ArrayList<Task> found = tasks.findTask(toFind);
        if (found.isEmpty()) {
            ui.printNoSimilarTasks();
            return;
        }
        ui.printTaskList(found, FIRST_LINE);
    }
}
