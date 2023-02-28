package psyduck.command;

import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        tasks.listTasks();
    }
}
