package duke.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }

    @Override
    public String saveText() {
        return super.saveText() + " | " + this.by;
    }
}




