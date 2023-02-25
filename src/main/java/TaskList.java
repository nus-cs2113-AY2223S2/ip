import duke.task.Task;

import java.util.ArrayList;

/**
 * List that contains all the current tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Calculates the current number of tasks in the task list
     *
     * @return total number of tasks in the task list
     */
    public int sizeOfTaskList() {
        return taskList.size();
    }

    /**
     * Adds tasks to task list
     *
     * @param task current task to be added to the task list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes tasks at specified index
     *
     * @param index index of task to be deleted
     */
    public void deleteTask(int index) {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }

    public void markAsDone(int index) {
        try {
            Task curTask = taskList.get(index);
            curTask.setAsDone();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }

    public void markAsUnDone(int index) {
        try {
            Task curTask = taskList.get(index);
            curTask.setAsUndone();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }

    /**
     * Gets the task from the task list at the specified index
     * @param index index of task to retrieved
     * @return task that the user wants
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
