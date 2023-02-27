package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * The DeleteTaskCommand class that inherits from command handles the task deletion command from the user
 */
public class DeleteTaskCommand extends Command {
    /**
     * The index of Task to be deleted from the TaskList
     */
    protected int deleteIndex;

    /**
     * Constructor of the DeleteTaskCommand that initialises the index to be deleted
     *
     * @param deleteIndex
     */
    public DeleteTaskCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * The execution of the DeleteTaskCommand that deletes the targeted task from the TaskList and Storage
     *
     * @param tasks    the TaskList where the targeted Task is to be deleted from
     * @param database the Storage where the targeted Task is to be deleted and database to be updated
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Ui.deleteTaskMessage(deleteIndex, tasks);
        tasks.deleteTaskFromTaskList(deleteIndex);
        try {
            database.updateDatabaseTask();
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }
}
