package duke;

/**
 * Represents a type of task that does not have any date/time
 * attached to it.
 */
public class Todo extends Task {


    /**
     * Constructs a To-do object that inherits from Task object.
     *
     * @param description The description of the to-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints To-do object in a specific format.
     *
     * @return A string representing the To-do object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}