package app.tasks;

public class ToDo extends Task {

    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    @Override
    public String toString() {
        return " [T][" + getStatusIcon() + "] " + taskDescription;
    }

}
