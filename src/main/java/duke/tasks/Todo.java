package duke.tasks;

/**
 * The Todo Type Task Class
 * */
public class Todo extends Task {

    /**
     * Creates Todo Task
     *
     * @param description The Todo Description
     * */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the Todo Task
     *
     * @return Todo Task
     * */
    @Override
    public String toString() {
        return "  [T]" + "[" + getStatusIcon() +"] " + super.getDescription();
    }
}