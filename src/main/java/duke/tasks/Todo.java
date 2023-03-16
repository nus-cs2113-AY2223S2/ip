package duke.tasks;

/**
 * Represent a to do task type
 */
public class Todo extends Task {
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Format the task into " T | task status | description"
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
        return "T | " + taskStatus + " | " + showTask() + "\n";
    }
}