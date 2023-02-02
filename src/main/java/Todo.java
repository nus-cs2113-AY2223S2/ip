public class Todo extends Task {
    public Todo(String TaskName) {
        super(TaskName);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

} //Todo class ends here