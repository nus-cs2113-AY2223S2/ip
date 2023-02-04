public class Todo extends Task {
    /***
     * Functions in the Todo class.
     * @param description Name of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /***
     * The string output when class is called.
     * @return Status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}