package command;

import exception.InvalidIndexException;
import storage.TaskStorage;
import task.Task;
import task.TaskList;
import ui.UI;

public class UnmarkCommand extends SaveCommand {
    private final int taskIdx;

    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        try {
            Task taskToUnmark = tasks.getTask(taskIdx);
            taskToUnmark.markAsUndone();
            ui.printMarkSuccess(taskToUnmark);
            super.execute(tasks,storage,ui);
        } catch (InvalidIndexException ex) {
            System.out.println("Exception occured : " + ex);
            System.out.println("Please specify the correct task to unmark");
        }
    }
}

