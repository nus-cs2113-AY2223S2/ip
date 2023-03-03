package tasks;

/**
 * Represents generic task
 */
public class Task {
    protected String name;
    protected Boolean isDone;

    /**
     *
     * @param name task name
     * @param isDone task isDone status
     */
    public Task (String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * @return String representation of the deadline for the file output
     */

    /**
     * @return String representation of the Task type
     */
    public String getType() {
        return "TASK";
    }
    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    /**
     * @return String representation of the task for the CLI output
     */
    public String toString() {
        String checker = this.isDone ? "[X]" : "[ ]";
        return checker + " " + this.name;
    }

    /**
     * @return String representation of the task for the file output
     */
    public String getFileFormatString() {
        return String.format("%s | %d | %s", getType(), isDone ? 1 : 0, name);
    }
}
