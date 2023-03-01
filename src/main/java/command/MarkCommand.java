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

    /**
     * Mark the {@link task.Task} in the {@link task.TaskList}, prints and saves it
     * @param tasks The {@link task.TaskList} that is responsible for modifying the ArrayList<Task>
     * @param storage The {@link storage.TaskStorage} that is responsible for saving and loading tasks
     * @param ui The {@link ui.UI} that is respoonsible for printing the output to the terminal
     */
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
