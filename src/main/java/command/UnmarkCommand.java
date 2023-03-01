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

    /**
     * Unmark the {@link task.Task} in the {@link task.TaskList}, prints and saves it
     * @param tasks The {@link task.TaskList} that is responsible for modifying the ArrayList<Task>
     * @param storage The {@link storage.TaskStorage} that is responsible for saving and loading tasks
     * @param ui The {@link ui.UI} that is respoonsible for printing the output to the terminal
     */
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        try {
            Task taskToUnmark = tasks.getTask(taskIdx);
            taskToUnmark.markAsUndone();
            ui.printUnmarkSuccess(taskToUnmark);
            super.execute(tasks,storage,ui);
        } catch (InvalidIndexException ex) {
            System.out.println("Exception occured : " + ex);
            System.out.println("Please specify the correct task to unmark");
        }
    }
}

