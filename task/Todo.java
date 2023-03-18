package task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
