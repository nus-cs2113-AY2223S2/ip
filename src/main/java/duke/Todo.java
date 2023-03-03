package duke;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * It formats the code and creates a task in the file
     * @return it returns the line that gets added to the file
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
