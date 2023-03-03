package Task;

public class Task {
    // attributes
    protected String description;
    protected boolean isDone;
    public static int numberOfTasks = 0;

    /**
     * Constuctor
     *
     * @param description task description
     * 
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        addNumberOfTasks();
    }

    /**
     * Constuctor
     *
     * @param description task description
     * @param isDone      whether the task is done or not
     * 
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        addNumberOfTasks();
    }

    /**
     * increment the number of tasks
     */
    public static void addNumberOfTasks() {
        numberOfTasks++;
    }

    /**
     * decrement the number of tasks
     */
    public static void decrementNumberOfTasks() {
        numberOfTasks--;
    }

    /**
     * getter for description
     * 
     * @return description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for status icon
     * 
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * mark task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * mark task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * toString method
     *
     * @return string description
     * 
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + "\t" + this.getDescription();
    }

    /**
     * toString method when saving in file
     *
     * @return string description to be saved in file
     * 
     */
    public String toFileString() {
        return "Task";
    }

}