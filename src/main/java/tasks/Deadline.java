package tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        if (by == null) {
            throw new IllegalArgumentException("Deadline \"by\" parameter cannot be null");
        }
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("D %s (by: %s)", super.toString(), getBy());
    }
}
