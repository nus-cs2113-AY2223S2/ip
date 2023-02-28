package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;
import psyduck.task.*;

public class AddToDoCommand extends Command {

    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        ToDo task = new ToDo(input[0]);
        tasks.addTask(task);
        Ui.printTaskAdded(tasks);
    }
}
