package command;

import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the mark command. inherit from Command class.
 */
public class MarkCommand extends Command {
    private final int taskNum;

    /**
     * build constructor for MarkCommand class.
     * @param taskNum task to be marked.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Method to execute the mark command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(taskNum - 1);
            task.setDone(true);
            ui.showMarked(task);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("please enter the right index (in the range of the tasks)");
        }
        return tasks;
    }
}
