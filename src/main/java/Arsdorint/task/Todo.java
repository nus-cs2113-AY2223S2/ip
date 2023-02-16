package Arsdorint.task;

public class Todo extends Task {
    public static final String typeToDo = "T";
    public Todo(String description) {
        super(description);
        this.taskName = typeToDo;
        this.taskType = "[T]";
    }
}
