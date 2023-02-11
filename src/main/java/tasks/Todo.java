package tasks;

public class Todo extends Task{
    private String type = "T";
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + type + "] [" + getStatus() + "] " + getTask();
    }
}
