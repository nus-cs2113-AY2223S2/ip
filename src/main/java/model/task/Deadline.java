package model.task;

/**
 * A deadline task
 */
public class Deadline extends Task {

    protected String endDate;

    public Deadline(String taskName, String endDate) {
        super(taskName);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String getDescriptionText() {
        String symbol = super.isDone() ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)",
                symbol,
                super.getTaskName(),
                this.endDate
        );
    }
}
