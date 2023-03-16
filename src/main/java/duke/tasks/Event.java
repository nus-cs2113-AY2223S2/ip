package duke.tasks;

/**
 * Represent an Event task type
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor of the object Event
     *
     * @param description task description
     * @param startTime   task start time
     * @param endTime     task end time
     * @param isCompleted task status
     */
    public Event(String description, String startTime, String endTime, boolean isCompleted) {
        super(description, isCompleted);
        this.from = startTime;
        this.to = endTime;
    }

    /**
     * Returns the string of start time of the event
     *
     * @return from the start time
     */
    public String getStartTime() {
        return from;
    }

    /**
     * Return the string of the end time of the event
     *
     * @return to the end time
     */
    public String getEndTime() {
        return to;
    }

    /**
     * Format the task into certain format
     *
     * @return taskLine a complete task line to be displayed
     */
    public String showTaskLine() {
        String taskStatus;
        if (isCompleted) {
            taskStatus = "[E][√] ";
        } else {
            taskStatus = "[E][ ] ";
        }

        String taskLine = taskStatus + showTask() + " "
                + "from: " + getStartTime() + " to: " + getEndTime();
        return taskLine;
    }

    /**
     * Format the task into " E | task status | description | starting time | ending time"
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
        String completeTaskLine = "E | " + taskStatus + " | " + showTask() + " | " +
                getStartTime() + " | " + getEndTime() + "\n";
        return completeTaskLine;
    }
}