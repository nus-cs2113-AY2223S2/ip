package IPChat;

/**
 * Class which mentions all the tasks performed by the capable IPChatBot
 *
 * @author DeepanjaliDhawan
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task object
     * @param description Provides a clear description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Status of the task
     * @return task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Method to return the string in the ArrayList
     * @return String in the ArrayList
     */
    public String toString() {
        return description;
    }

    /**
     * Method to save the tasktype in the file
     * @return description of the task
     */
    public String saveStuff() {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "taskType" + description +  " | " +  save + "\n";
    }
}
