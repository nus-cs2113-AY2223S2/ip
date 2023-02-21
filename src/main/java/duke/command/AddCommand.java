package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * AddCommand class that inherits from the Command class
 * Refers to the add task type of Command
 */
public abstract class AddCommand extends Command {
    /**
     * Saves newly added task to database
     *
     * @param currTask refers to the Task that is being added
     * @param database to be used to store the newly added task
     */
    public void addTaskToDatabase(Task currTask, Storage database) {
        try {
            database.saveAddTask(currTask.getTaskString());
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }

    /**
     * Adds the new task to the TaskList
     *
     * @param tasks    the TaskList for the task to be added to
     * @param currTask the newly created Task Object
     */
    public void addTaskToTaskList(TaskList tasks, Task currTask) {
        tasks.addTaskToTaskList(currTask);
        Ui.addSpecialTaskMessage(tasks);
    }
}
