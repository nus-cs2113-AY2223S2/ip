package duke.task;

/**
 * Represents a general task. All task objects have their specific types , status
 * and their task descriptions
 */
public abstract class Task {
    public String description;

    protected String taskType;

    protected boolean isDone;

    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Getter function of the status icon
     *
     * @return "[X]" if it is done and "[]" if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]");
    }

    public String toString() {
        return getStatusIcon() + " " + description;

    }

    public String textToSave() {
        return "";
    }
}


