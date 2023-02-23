public class Event extends Task {
    protected String startDate;
    protected String endDate;
    protected String type;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = "E";
    }

    public String addedMessage() {
        ++taskCount;
        return " Got it. I've added this task:\n" + "   [E]"
                + getStatusIcon() + " " + description + "(from: " + startDate + "to: " + endDate + ")\n"
                + " Now you have " + Task.taskCount + (Task.taskCount != 1 ? " tasks" : " task") + " in the list.";
    }

    public String statusMessage() {
        return "[E]" + getStatusIcon() + " " + description + "(from: " + startDate + "to: " + endDate + ")";
    }
}
