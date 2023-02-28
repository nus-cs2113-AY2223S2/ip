package Duke.Tasks;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String saveInfo() {
        return getTaskType() + "t/" +
                (isDone ? "X" : "Y") + "m/" +
                taskName + "\n";
    }
}
