package command;

import exception.InvalidIndexException;
import storage.TaskStorage;
import task.TaskList;
import ui.UI;
import task.Task;

public class MarkCommand extends SaveCommand  {
    private final int taskIdx;

    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        try {
            Task taskToMark = tasks.getTask(taskIdx);
            taskToMark.markAsDone();
            ui.printMarkSuccess(taskToMark);
            super.execute(tasks,storage,ui);
        } catch (InvalidIndexException ex) {
            System.out.println("Please specify the correct task to mark");
            System.out.println("Exception occured : " + ex);
        }
    }
}
