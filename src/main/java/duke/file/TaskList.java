package duke.file;

import duke.tasks.Task;
import duke.ui.*;
import duke.outputs.Messages;

import java.util.ArrayList;

/**
 * List that contains all the current tasks
 */
public class TaskList {
    private ArrayList<Task> tasksArray;

    public ArrayList<Task> getTasksArray() {
        return tasksArray;
    }

    public TaskList() {
        this.tasksArray = new ArrayList<>();
    }

    /**
     * Calculates the current number of tasks in the task list
     *
     * @return total number of tasks in the task list
     */
    public int sizeOfTasksArray() {
        return tasksArray.size();
    }

    /**
     * Adds tasks to task list
     *
     * @param task current task to be added to the task list
     */
    public void addNewTask(Task task) {
        tasksArray.add(task);
    }

    /**
     * Deletes tasks at specified index
     *
     * @param index index of task to be deleted
     */
    public void deleteSpecifiedTask(int index) {
        try {
            tasksArray.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskErrorMessage();
        }
    }

    public void markedAsDone(int index) {
        try {
            Task curTask = tasksArray.get(index);
            curTask.markAsDone();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskErrorMessage();
        }
    }

    public void markedAsUnDone(int index) {
        try {
            Task curTask = tasksArray.get(index);
            curTask.markAsUndone();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskErrorMessage();
        }
    }

    /**
     * Gets the task from the task list at the specified index
     * @param index index of task to retrieved
     * @return task that the user wants
     */
    public Task getTask(int index) {
        return tasksArray.get(index);
    }
}
