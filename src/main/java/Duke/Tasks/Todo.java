package Duke.Tasks;

/**
 * Todo is a task without more attributes added.
 */
public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
