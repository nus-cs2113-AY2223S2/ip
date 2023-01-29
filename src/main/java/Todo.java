public class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    @Override
    public String describeTask() {
        return "[T]" + super.describeTask();
    }
}