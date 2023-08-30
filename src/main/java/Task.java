/**
 * Represents a task that the user has inputted. A <code>Task</code> object contains a
 * basic <code>description</code> and <code>isDone</code> to indicate completion.
 */

public class Task extends TaskArray{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }
    /**
     * Returns the contents of task in an appropriate string format for printing.
     * @return Formatted string output.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    /**
     * Returns the contents of task in an appropriate string format for adding to text file.
     * @return Formatted string output.
     */
    public String toFile() {
        return this.isDone + " | " + this.description + "\n";
    }
}