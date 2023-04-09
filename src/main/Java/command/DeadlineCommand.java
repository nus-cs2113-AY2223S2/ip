package command;

import task.Deadline;
import jonathan.Storage;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the deadline command. inherit from Command class.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * build constructor for DeadlineCommand class.
     * @param deadline task to be executed.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Method to execute the bye command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(deadline);
        ui.showAdded(tasks, deadline);
        return tasks;
    }
}
