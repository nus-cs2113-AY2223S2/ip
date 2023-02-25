package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

import java.io.IOException;

/**
 * Represents an executable command from the user. A <code>AddCommand</code> object contains the details of the task to
 * be added. This object adds a task to the TaskList when the execute method is called.
 */
public class AddCommand extends Command {
    /** Task details */
    private String taskType;
    private String taskName;
    private String taskDates;
    public AddCommand(String taskType, String taskName, String taskDates) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskDates = taskDates;
    }

    /**
     * This method adds a task to the TaskList, it then saves the TaskList.
     * If the task details are valid, this method adds the task toe the TaskList and prints a string to inform the user
     * that a new task has been added.
     * If a task details are not valid, this method informs the user that they have entered an invalid command.
     *
     * @param tasks The taskList containing all the tasks in Luke.
     * @param ui A Ui object which handles outputs to the user.
     * @param storage An object responsible for saving information in Luke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isAdded = tasks.addTask(taskType, taskName, taskDates);

        if (!isAdded) {
            new InvalidCommand().execute(tasks, ui, storage);
            return;
        }

        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            storage.handleSaveError();
        }
        ui.printAddTask(taskName);
    }
}
