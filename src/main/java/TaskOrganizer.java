import java.util.ArrayList;
import java.util.HashMap;

public class TaskOrganizer {
    /** Unique ID to be given to each new task for identification */
    private int newTaskID;

    /** A container containing all tasks added */
    private HashMap<Integer, Task> tasks;

    public TaskOrganizer() {
        newTaskID = 1;
        tasks = new HashMap<Integer, Task>();
    }

    /**
     * Checks if the index provides is out of bounds from the current available tasks.
     *
     * @param index The index provided.
     * @return True if the index provided is out of bounds, False if the index provides is not out of bounds.
     */
    public boolean isOutOfBounds(int index) {
        return (index <= 0 || index >= newTaskID);
    }

    /**
     * Checks if tasks is empty.
     *
     * @return True if tasks is empty, False if tasks is not empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param taskName The name of the task to be added to the list tasks.
     */
    public void addTask(String taskName) {
        Task newTask = new Task(taskName, newTaskID);
        tasks.put(newTaskID, newTask);
        newTaskID += 1;
    }

    /**
     * Given the ID of a task, this function marks the corresponding task as completed.
     *
     * @param toMarkID The ID of the task to be marked.
     */
    public void markTask(int toMarkID) {
        tasks.get(toMarkID).markTask();
    }

    /**
     * Given the ID of a task, this function unmarks a task.
     *
     * @param toUnmarkID The ID of the task to be unmarked.
     */
    public void unmarkTask(int toUnmarkID) {
        tasks.get(toUnmarkID).unmarkTask();
    }

    /**
     * Returns the name of a task, given its ID.
     *
     * @param taskID The ID of the task.
     * @return The name of the task with taskID.
     */
    public String getTaskByID(int taskID) {
        return tasks.get(taskID).getTaskName();
    }

    /**
     * Returns an ArrayList of Tasks in the order they are added.
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> copy = new ArrayList<Task>();
        for (int i = 1; i < newTaskID; i++) {
            copy.add(tasks.get(i));
        }
        return copy;
    }
}
