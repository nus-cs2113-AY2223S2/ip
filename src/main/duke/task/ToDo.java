package  duke.task;
public class ToDo extends Task {

    /**
     * Initializes a task object of type todo.
     * Sets the task object's type to T.
     *
     * @param task The contents of the task object.
     * @param isDone The initialize status marked/unmarked of the task object.
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
        this.type = "T";
    }
}
