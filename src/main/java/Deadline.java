/**
 * Represents a task with a deadline
 *
 * @author Wen Jun
 * @version 1.0
 */
public class Deadline extends Task {

    protected String endDate;

    public Deadline(String taskName, String endDate) {
        super(taskName);
        this.endDate = endDate;
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
