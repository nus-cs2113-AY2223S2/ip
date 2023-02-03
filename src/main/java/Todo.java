public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

} //Todo class ends here