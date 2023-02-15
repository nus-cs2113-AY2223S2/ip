package Duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.taskName = description;
        this.isCompleted = false;
        this.taskType = "T";
    }

}
