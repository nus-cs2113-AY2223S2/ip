package Onandon.module;

/**
 * Representation of the todo class.
 */
public class Todo extends Task {

    /**
     * Create new Todo class with the specified configuration.
     *
     * @param description Description of the command.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Print the state of the Todo class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.description;
    }
}