package duke;

/**
 * The task superclass
 */
public class Task {
    final String description;
    boolean isDone;

    TaskType type;

    /**
     * Constructor for a Task object
     * @param desc description of the task
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Gets a formatted label of the task
     * @return the formatted string
     */
    public String getLabel() {
        String typeIndicator = "[T]";
        String doneIndicator = "[" + (this.isDone ? "X" : " ") + "]";
        return typeIndicator + doneIndicator + " " + this.description;
    }

    /**
     * Marks the task as either done or not done
     * @param isDone the new done status of the task
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

enum TaskType {
    TODO, EVENT, DEADLINE
}
