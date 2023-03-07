package alltasks;

public class Deadline extends Task {

    private String by;

    public Deadline(String descriptor, String by) {
        super(descriptor);
        this.by = by;
    }

    @Override
    public String getInfo() {
        return String.format("%s|%s|%s|%s", "Deadline", this.isDone, this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "]" + " " + description + " by: " + this.by;
    }
}
