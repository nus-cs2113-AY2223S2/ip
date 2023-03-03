package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol = "[T]";

    public String getDescription() {
        return description;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbol of the task
     * @return
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the boolean value of whether a task has been marked as done or not
     * @return true if the task has been marked as done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * marks the task as not done
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task has been marked as done and returns the appropriate symbol
     * @return returns "[X]" if done, and  "[ ]" if not done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns a string representation of the task to be presented to the user
     * @return the formatted string output
     */
    @Override
    public String toString(){
        return "[T]" + this.getStatusIcon() + " " + getDescription();
    }

    /**
     * Returns a string representation of the task to be stored in the text file
     * @return the formatted string output
     */
    public String toFile() {
        return this.getStatusIcon() + " : " +"T"+ " : " + this.description;
    }
}


