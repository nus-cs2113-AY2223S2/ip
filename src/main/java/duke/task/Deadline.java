package duke.task;

public class Deadline extends Task {
    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String taskOutput = super.toString();
        return String.format("[D]%s (by: %s)", taskOutput, endDate);
    }
}
