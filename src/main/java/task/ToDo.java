package task;

/**
 * This class is for the ToDo task which is a subclass of task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String printToFile() {
        return "T | " + this.saveStatusIcon() + " | " + description;
    }
}
