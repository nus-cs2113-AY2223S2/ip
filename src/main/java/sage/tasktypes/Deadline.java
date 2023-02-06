package sage.tasktypes;

public class Deadline extends Task {
    private String byWhen = "";

    public Deadline(String taskName, String byWhen) {
        super(taskName);
        this.byWhen = byWhen;
    }

    public String getByWhen() {
        return byWhen;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return ".[D][X] " + super.getTaskName() + "(by: " + getByWhen() + ")";
        } else {
            return ".[D][ ] " + super.getTaskName() + "(by: " + getByWhen() + ")";
        }
    }
}
