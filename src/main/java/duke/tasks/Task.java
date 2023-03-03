package duke.tasks;

/**
 * Represents tasks set by user
 * Keeps track of the task type, description, dates and status
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String type;
    protected String start;
    protected String end;

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Checks whether the task is done and returns the correct icon to represent the status of the task
     * @return 'X' if the task is done and ' ' if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Prints the Task in the correct format required in the list
     * The format is determined by the type of task
     */
    public void printTask() {
        System.out.println("task");
    }
}
