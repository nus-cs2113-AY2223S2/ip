package  duke.task;
public class ToDo extends Task {
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
        this.type = "T";
    }
}
