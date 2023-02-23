public class ToDo extends Task {
    public String type;

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public String addedMessage() {
        ++taskCount;
        return " Got it. I've added this task:\n" + "   [T]"
                + getStatusIcon() + " " + description + "\n" + " Now you have " + Task.taskCount +
                (Task.taskCount != 1 ? " tasks" : " task") + " in the list.";
    }

    public String statusMessage() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
