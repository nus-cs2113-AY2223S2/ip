public class Deadline extends Task {
    private String byWhen = "";

    public Deadline(String taskName, String byWhen) {
        super(taskName);
        this.byWhen = byWhen;
    }

    public String getByWhen() {
        return byWhen;
    }

    public String toString() {
        if (super.IsCompleted()) {
            return ".[D][X] " + super.GetTaskName() + "(by: " + getByWhen() + ")";
        } else {
            return ".[D][ ] " + super.GetTaskName() + "(by: " + getByWhen() + ")";
        }
    }
}
