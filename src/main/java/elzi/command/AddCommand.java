package elzi.command;

import elzi.TaskList;
import elzi.Ui;
import elzi.task.Task;

/**
 * @author : Steven A. O. Waskito
 * Class to add command todo, deadline, event
 **/
public class AddCommand extends Command {
    private Task task;

    /**
     * Sets the task to add
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to taskList
     * @param taskList taskList ArrayList that stores tasks
     */
    @Override
    public boolean execute(TaskList taskList) {
        taskList.addTask(task);
        Ui.printAddTask(task);
        Ui.printTaskLeft(taskList);
        return false;
    }
}
