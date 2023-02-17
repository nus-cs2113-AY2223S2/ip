package task;

/**
 * Represents an Todo type of task that only has details keyed in by the user.
 */
public class Todo extends Task {

    public Todo(String details) {
        super(details);
    }

    /**
     * Method to print Todo objects in a certain manner.
     *
     * @return a string that represents the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}