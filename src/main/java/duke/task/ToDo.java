package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        return super.toString() + getType() + getStatusIcon() + getDescription();
    }
}
