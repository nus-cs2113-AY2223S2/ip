public class ToDo extends Task {
    public String type;

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Increases the taskCount and generates the corresponding added message.
     * @return String that corresponds to added message
     */
    public String addedMessage() {
        ++taskCount;
        return " Got it. I've added this task:\n" + "   [T]"
                + getStatusIcon() + " " + description + "\n" + " Now you have " + Task.taskCount +
                (Task.taskCount != 1 ? " tasks" : " task") + " in the list.";
    }
    /**
     * Gets the status of the task
     * @return String that displays the task and status of the task
     */
    public String statusMessage() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
