package Arsdorint.command;

import Arsdorint.data.TaskList;
import Arsdorint.task.Task;

import java.util.ArrayList;

/**
 * The parent class Command
 */
public abstract class Command {
    public String commandType;
    protected String message;
    protected TaskList taskList = new TaskList();
    protected ArrayList<Task> taskListTarget = new ArrayList<>();
    protected Command(String commandType) {
        this.commandType = commandType;
    }
    /**
     * Execution of the command
     */
    public abstract CommandRes execute();

    /**
     * Check if the key is contained in the index of the tasks
     */
    public boolean contains(final int[] arr, final int key) {
        for (final int i : arr) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set the tasklist that the command will act on
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Count the target list that the command act on
     */
    protected int targetCount() {
        return (int) taskListTarget.stream().count();
    }
}
