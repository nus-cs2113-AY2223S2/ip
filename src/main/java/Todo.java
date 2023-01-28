/**
 * Represents a task that does not have any deadlines
 *
 * @author Wen Jun
 * @version 1.0
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getDescriptionText() {
        String symbol = super.isDone() ? "X" : " ";
        return String.format("[T][%s] %s", symbol, super.getTaskName());
    }
}
