package duke;

public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    @Override
    public String getLabel() {
        String typeIndicator = "[D]";
        String doneIndicator = "[" + (this.isDone ? "X" : " ") + "]";
        String suffix = "(by: " + this.by + ")";
        return typeIndicator + doneIndicator + " " + this.description + " " + suffix;
    }
}
