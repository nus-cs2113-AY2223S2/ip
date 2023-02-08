package duke.task;

public class ToDo extends Task{
    public ToDo(int Index, String description) {
        super(Index, description);
    }

    public String toString() {
        return super.toString() + "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
