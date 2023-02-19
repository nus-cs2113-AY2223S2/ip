package duke.tasks;

public class Todo extends Task implements java.io.Serializable {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return taskTypeBoxFormat() + markedBoxFormat() + " " + getTaskName();
    }

    @Override
    public String taskTypeBoxFormat() {
        return "[T]";
    }
}
