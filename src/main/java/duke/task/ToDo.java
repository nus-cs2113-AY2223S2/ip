package  duke.task;
public class ToDo extends Task {
    public ToDo(String task, int number, boolean isDone) {
        super(task, number, isDone);
        this.type = "T";
    }
}
