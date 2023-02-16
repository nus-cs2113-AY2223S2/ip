package duke;

/**
 * Represents an Todo type of task that only has details keyed in by the user.
 */
public class Todo extends Task {

    public Todo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}