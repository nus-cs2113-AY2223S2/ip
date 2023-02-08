public class Deadline extends Task {
    private String due;

    public String getDue() {
        return due;
    }

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public String getTaskType() {
        return "D";
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " (by: " + getDue() + ")";
    }
}
