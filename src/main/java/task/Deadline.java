package task;

public class Deadline extends Task {
    private final String due;

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

    @Override
    public String getDataSummary() {
        return super.getDataSummary() + " | " + getDue();
    }
}
