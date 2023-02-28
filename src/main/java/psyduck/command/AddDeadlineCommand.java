package psyduck.command;

import psyduck.task.Deadline;
import psyduck.tasklist.TaskList;
import psyduck.ui.Ui;

public class AddDeadlineCommand extends Command {
    @Override
    public void executeCommand(String[] input, TaskList tasks, Ui ui) {
        Deadline task = new Deadline(input[0], input[1]);
        tasks.addTask(task);
        Ui.printTaskAdded(tasks);
    }
}
