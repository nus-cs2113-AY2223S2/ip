package task;

public class Deadline extends Task {
    private String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }

    @Override
    public String printToFile() {
        return "D | " + this.saveStatusIcon() + " | " + description + " | " + byDate;
    }
}
