package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }


    /**
     * Printing the Task that includes whether it is done
     *
     * @return Returns a string that describes the object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
