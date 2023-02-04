package btb.tasks;

public class Todo extends Task {

    /**
     * Constructs a new instance of Todo object and
     * stores description and status of the object.
     *
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Task object to a string
     *
     * @return the string that was converted
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}