public class Deadline extends Task {
    String deadline;
    public Deadline (String description, String deadline) {
        super(description + "(by: " + deadline + ")");
        this.deadline = deadline;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }
}
