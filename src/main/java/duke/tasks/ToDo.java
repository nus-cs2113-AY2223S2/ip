package duke.tasks;

public class ToDo extends Task {
    // provide the details relating to this toDo Task.
    public ToDo(String taskName) {
        super(taskName); // invoke superclass constructor
    }

    @Override
    public String listTask() {
            return taskTypeBoxFormat() + markedBoxFormat() + " " + getTaskName();
    }
    @Override
    public String taskTypeBoxFormat() {
        return "[T]";
    }
}
