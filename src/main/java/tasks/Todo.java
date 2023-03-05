package tasks;

/**
 * This is the Todo Task Class, Stores a task with a description
 * */
public class Todo extends Task {

    /**
     * Factory function for todo task
     * */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]" + super.getDescription().toString();
    }
}
