package duke;

/**
 * Represents a Todo task type. A <code>Todo</code> object corresponds
 * to a <code>Task</code> object that requires only a description. This is
 * provided by the user via the additional words after the <code>/todo</code>
 * command e.g.,
 * <code>/todo do CS2113 assignment</code>
 */
public class Todo extends Task {
    public Todo(String arguments) {
        super();
        String description = arguments.trim();
        super.description = description;
    }

    @Override
    public String formattedString() {
        String formatted = "Todo:" + super.isDone + ":" + super.description;
        return formatted;
    }

    @Override
    public String toString() {
        return "[TODO]\n" + super.toString();
    }
}
