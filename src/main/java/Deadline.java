public class Deadline extends Task {
    protected String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    public String addedMessage() {
        return " Got it. I've added this task:\n" + "   [D]"
                + getStatusIcon() + " " + description + "(by: " + byDate + ")\n"
                + " Now you have " + Task.taskCount + (Task.taskCount != 1 ? " tasks" : " task") + " in the list.";
    }

    public String statusMessage() {
        return "[D]" + getStatusIcon() + " " + description + "(by: " + byDate + ")";
    }
}
