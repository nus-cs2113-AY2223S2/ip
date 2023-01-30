public class Todo extends Task {
    public Todo(String description, int taskID) {
        super(description, taskID);
        taskCount += 1;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}