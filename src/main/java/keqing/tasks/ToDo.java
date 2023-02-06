package keqing.tasks;

public class ToDo extends Task {
    public ToDo(String description, int taskID) {
        super(description, taskID);
        taskCount += 1;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}