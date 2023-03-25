/*
 * Sub-class of super-class <code>Task</code>, represents a todo task,
 * only inherits task description attribute from parent Task class
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T][" + super.getStatusIcon() + "] " + super.description);
    }

    @Override
    public String fileFormat() {
        return (String.format("T|%b|%s\n", super.isDone, this.description));
    }
}
