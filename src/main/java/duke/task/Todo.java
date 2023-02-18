package duke.task;

public class Todo extends Task {

    /**
     * Constructor to initialise description of todo task.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {

        super(description);
    }

    /**
     * Returns the string in the required format.
     *
     * @return Task description in the required format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
