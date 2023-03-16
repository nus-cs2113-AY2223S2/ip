package duke.tasks;

/**
 * Represents a deadline task type
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor of a Deadline object
     *
     * @param description task description
     * @param by          deadline
     * @param isComplete  task status
     */
    public Deadline(String description, String by, boolean isComplete) {
        super(description, isComplete);
        this.by = by;
    }

    /**
     * Return a string of deadline
     *
     * @return by the deadline
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string af task line
     * Format the task into certain format
     *
     * @return taskLine a complete task line to be displayed
     */
    public String showTaskLine() {
        String taskStatus;
        if (isCompleted) {
            taskStatus = "[D][√] ";
        } else {
            taskStatus = "[D][ ] ";
        }
        return taskStatus + showTask() + " due: " + getBy();
    }

    /**
     * Return a string of complete task line
     * Format the task into " D | task status | description | deadline"
     *
     * @return completeTaskLine a complete message line to be written in the file
     */
    public String writeTaskLine() {
        String taskStatus;
        if (!isCompleted) {
            taskStatus = "0";
        } else {
            taskStatus = "1";
        }
        String completeTaskLine = "D | " + taskStatus + " | " + showTask() + " | " + getBy() + "\n";
        return completeTaskLine;
    }
}