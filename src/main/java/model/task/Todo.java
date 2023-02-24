package model.task;

/**
 * A Todo task that extends the Task
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
