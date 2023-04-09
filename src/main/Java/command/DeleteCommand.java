package command;

import jonathan.JonathanException;
import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the delete command. inherit from Command class.
 */
public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Method to execute the delete command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     * @throws JonathanException when the task doesn't exist.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws JonathanException {
        try {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(taskNum - 1);
            ui.showDelete(tasks, task);
            return tasks;
        } catch (IndexOutOfBoundsException e) {
            throw new JonathanException("The task doesn't exist, please type it correctly!");
        }

    }
}
