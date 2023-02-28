package duke;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
        this.type = TaskType.TODO;
    }
}

