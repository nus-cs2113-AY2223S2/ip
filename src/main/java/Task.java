/**
 * Represents a general task under no specific category that the user wishes to take note of in their list.
 */

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return this.description;
    }

    public String getTaskIcon() {
        return "";
    }

    public String getDeadlineBy() {
        return "";
    }

    public String getEventStart() {
        return "";
    }

    public String getEventEnd() {
        return "";
    }

    /**
     * Checks whether a task has been completed
     * @return "X" if task has been done or a whitespace character if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusNum() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Overrides the Object class's toString method to return a more informative string that better reflects the user's task.
     * @return A formatted string of the task's description and done status.
     */
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "]" + description;
    }
}
