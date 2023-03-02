package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    @Override
    public String toString() {
        return "[T][" + getComplete() + "] " + getTask();
    }
}


