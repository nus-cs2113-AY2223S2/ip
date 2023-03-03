package nano.data.task;

public class Todo extends Task{
    private static final String TASK_TYPE_TAG = "[T]";

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return TASK_TYPE_TAG + super.toString();
    }
}
