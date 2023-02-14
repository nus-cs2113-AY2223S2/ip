package duke.tasks;

public class ToDo extends Task implements java.io.Serializable {
    // provide the details relating to this toDo Task.
    public ToDo(String taskName) {
        super(taskName); // invoke superclass constructor
    }

    @Override
    public String listTask() {
        if (getIsComplete()) {
            return "[T][X] " + getTaskName();
        } else {
            return "[T][] " + getTaskName();
        }
    }

}
