package duke.tasklist;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
        this.type = "T";
    }
}
