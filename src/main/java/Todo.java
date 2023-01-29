/**
 * Represents tasks without any date/time attached to it.
 * e.g., visit new theme park.
 */
public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }
    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
