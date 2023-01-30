public class ToDo extends Task {
    // provide the details relating to this toDo Task.
    public ToDo(String taskName) {
        super(taskName); // invoke superclass constructor
    }

    public String listTask() {
        if (getIsComplete()) {
            return "[T][X] " + getTaskName();
        } else {
            return "[T][] " + getTaskName();
        }
    }

}
