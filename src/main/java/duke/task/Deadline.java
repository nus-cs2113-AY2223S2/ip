package duke.task;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toFileFormat() {
        return "D | " + isMarked + " | " + description + " | " + deadline + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.description + "(by:" + deadline + ")";
    }
}
