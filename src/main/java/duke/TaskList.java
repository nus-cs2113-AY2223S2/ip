package duke;
import java.util.ArrayList;

/**
 * Stores all tasks added by user. Handles accessing the task list.
 */
public class TaskList {
    private ArrayList<Task> storedTasks;
    private int taskNum;
    public TaskList() {
        storedTasks = new ArrayList<>();
        taskNum = 0;
    }
    public void incrementTaskNum() {
        taskNum++;
    }
    public int getTaskNum() {
        return taskNum;
    }
    public ArrayList<Task> getStoredTasks() {
        return storedTasks;
    }

    /**
     * Updates taskList to match save file.
     * @param newTasks tasks in taskList is updated with new info from dave file.
     */
    public void setStoredTasks(ArrayList<Task> newTasks) {
        storedTasks = newTasks;
    }
    public void removeItem(int index) {
        storedTasks.remove(index);
    }
    public void addItem(Task task) {
        storedTasks.add(task);
    }
}
