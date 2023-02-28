package duke.tasktypes;

/**
 * Represents a task in the application.
 */
public abstract class Task {

    protected String content;
    private boolean isMarked;

    /**
     * Initializes the content and marking status of the task.
     * @param content the content of the task.
     */
    public Task(String content) {
        this.content = content;
        this.isMarked = false;
    }

    /**
     * Sets the task to marked.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Sets the task to unmarked.
     */
    public void unMark() {
        this.isMarked = false;
    }

    /**
     * Returns the marking status of the task.
     * @return the marking status of the task as a string.
     */
    public String getMarkingStatus() {
        return isMarked ? "[X]" : "[ ]";
    }

    /**
     * Converts the marking status of the task to a number.
     * @return the marking status of the task as a number.
     */
    public String convertMarkingStatusToNumber() {
        if (this.getMarkingStatus().equals("[ ]")) {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * Returns the content of the task.
     * @return the content of the task as a string.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Returns the task in a formatted string.
     * @return the formatted string representation of the task as a string.
     */
    public abstract String formatTaskToPrint();

    /**
     * Formats the task data to be written to the data file.
     * @return the formatted task data to be written to the data file as a string.
     */
    public abstract String putInputToDataFile();

}
