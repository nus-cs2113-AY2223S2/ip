public class Deadline extends Task {
    protected String byDate;
    protected String type;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
        this.type = "D";
    }

    /**
     * Increases the taskCount and generates the corresponding added message.
     * @return String that corresponds to added message
     */
    public String addedMessage() {
        ++taskCount;
        return " Got it. I've added this task:\n" + "   [D]"
                + getStatusIcon() + " " + description + "(by: " + byDate + ")\n"
                + " Now you have " + Task.taskCount + (Task.taskCount != 1 ? " tasks" : " task") + " in the list.";
    }

    /**
     * Gets the status of the task
     * @return String that displays the task and statuses of the task
     */
    public String statusMessage() {
        return "[D]" + getStatusIcon() + " " + description + "(by: " + byDate + ")";
    }
}
