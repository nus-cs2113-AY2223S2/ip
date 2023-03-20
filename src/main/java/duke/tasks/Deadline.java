package duke.tasks;

public class Deadline extends Task {

    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTask() {

        return String.format("[D]" + super.getTask() + " by: " + by);
    }
}
