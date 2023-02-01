public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String addedMessage() {
        return " Got it. I've added this task:\n" + "   [T]"
                + getStatusIcon() + " " + description + "\n" + " Now you have " + Task.taskCount +
                (Task.taskCount != 1 ? " tasks" : " task") + " in the list.";
    }

    public String statusMessage() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
