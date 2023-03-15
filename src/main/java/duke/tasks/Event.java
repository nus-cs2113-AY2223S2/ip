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
    public String showTask() {
        String taskStatus;
        if (isCompleted == true) {
            taskStatus = "[E][√] ";
        } else {
            taskStatus = "[E][ ] ";
        }
        String time = "from: " + getStartTime() + " to: " + getEndTime();
        String taskLine = taskStatus + getDescription() + " " + time;
        return taskLine;
    }

    /**
     * Format the task into " E | task status | description | starting time | ending time"
     *
     * @return completeTaskLine a complete message line to be written in the file
     */
    public String writeTask() {
        String taskStatus;
        if (isCompleted == false) {
            taskStatus = "0";
        } else {
            taskStatus = "1";
        }
        String completeTaskLine = "E | " + taskStatus + " | " + getDescription() + " | " +
                getStartTime() + " | " + getEndTime() + "\n";
        return completeTaskLine;
    }
}