package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }

    @Override
    public String saveTask() {
        return ("T" + "//" + checkCompletion() + "//" + getDescription() + "\n");
    }

}
