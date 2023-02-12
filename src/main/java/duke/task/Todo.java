package duke.task;

public class Todo extends Task {
    public Todo(String desciption) {
        super(desciption);
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
