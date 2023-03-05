/**
 * The Task class is the parent class for Events, Deadlines and ToDos.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to get the status icon.
     * If status is no done, " " is returned.
     *
     * @return String This returns status based on isDone.
     */
    public String getStatusIcon() {
        return (isDone ? StrIntLib.done : StrIntLib.notDone);
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * This method is to be overridden in child classes.
     *
     * @return String This returns a space.
     */
    public String getIcon() {
        return " ";
    }

    /**
     * This method is to be overridden in Events class.
     *
     * @return String This returns an empty string.
     */
    public String getStart() {
        return "";
    }

    /**
     * This method is to be overridden in Events class.
     *
     * @return String This returns an empty string.
     */
    public String getEnd() {
        return "";
    }

    /**
     * This method is to be overridden in Deadlines class.
     *
     * @return String This returns an empty string.
     */
    public String getDeadline() {
        return "";
    }
}

