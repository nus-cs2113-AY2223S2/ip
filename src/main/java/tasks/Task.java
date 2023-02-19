package tasks;

/**
 * Represents a task. A <code>Task</code> is an abstract object with three specific types,
 * <code>Todo</code>, <code>Deadline</code> and <code>Event</code>.
 */
public abstract class Task {
    private static int itemCount = 0;
    private String itemName;
    private boolean isDone;

    /**
     * Creates an object of type Task.
     *
     * @param itemName The name of this Task object.
     */
    public Task(String itemName) {
        itemName = itemName.substring(0,1).toUpperCase() + itemName.substring(1);
        this.itemName = itemName.trim();
        this.isDone = false;
    }

    /**
     * Returns the name of the current Task Object.
     *
     * @return Name of Task Object.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Return the total number of Task Objects that are created.
     *
     * @return Total number of Task Objects.
     */
    public static int getItemCount() {
        return itemCount;
    }

    /**
     * Increases the count for the number of Task Objects.
     * Used whenever a new Task is added.
     */
    public static void incrementItemCount() {
        Task.itemCount++;
    }

    /**
     * Decreases the count for the number of Task Objects.
     * Used whenever a Task is deleted.
     */
    public static void decreaseItemCount() {
        Task.itemCount--;
    }

    /**
     * Return if the Task have been marked as done.
     *
     * @return True if the Task is marked as done, else return false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark the current Task as completed if <code>state</code> is <code>true</code>,
     * else mark it as incomplete, by changing the state of <code>isDone</code>.
     *
     * @param state The state that you wish to mark the Current Task to.
     */
    public void markAsState(boolean state) {
        this.isDone = state;
    }

    /**
     * Print the contents of the current Task in a format specific to the type of
     * Task it is. This print includes a dot for a number representing the index of
     * the currently printed Task to be included before it.
     */
    public abstract void printTask();

    /**
     * Print the contents of the Task in a format specific to the type of
     * Task it is. This print is specifically used when the task is to be printed
     * without an integer representing the number of this task before it.
     */
    public abstract void printTaskWithoutId();

    /**
     * Return the type of the current Task.
     * @return T if task is of <code>Todo</code> type
     *         D if task is of <code>Deadline</code> type
     *         E if task is of <code>Event</code> type
     */
    public abstract String getClassType();

    /**
     * Return a String containing the contents of the task that is in a format
     * suitable for storing in a text file.
     *
     * @return A string containing the essential information of the Task, such as
     *         name of the Task or if it is marked as done.
     */
    public abstract String getToStore();
}
