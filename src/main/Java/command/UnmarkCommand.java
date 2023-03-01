package command;

import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the unmark command. inherit from Command class.
 */
public class UnmarkCommand extends Command {
    private final int taskNum;

    /**
     * build constructor for MarkCommand class.
     * @param taskNum task to be marked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Method to execute the unmark command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNum - 1);
        task.setDone(false);
        ui.showUnmarked(task);
        return tasks;
    }
}
