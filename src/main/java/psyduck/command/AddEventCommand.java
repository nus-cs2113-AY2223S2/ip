package psyduck.command;

import psyduck.task.Event;
import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;

public class AddEventCommand extends Command {

    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        Event task = new Event(input[0], input[1], input[2]);
        tasks.addTask(task);
        Ui.printTaskAdded(tasks);
    }
}
