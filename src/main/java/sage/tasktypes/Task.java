package sage.tasktypes;


/**
 * A parent class that represents a task's description and completion status.
 */
public class Task {
    private String taskDetails = "";
    private boolean isCompleted = false;

    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
        this.isCompleted = false;
    }

    public String getTaskDetails() {
        return this.taskDetails;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public char printStatusSymbol() {
        if (isCompleted) {
            return 'X';
        }
        return ' ';
    }

    public String toString() {
        return "";
    }

    ;
}
