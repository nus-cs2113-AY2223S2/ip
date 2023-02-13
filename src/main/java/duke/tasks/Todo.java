package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    //    public String getType() {
    //
    //    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
