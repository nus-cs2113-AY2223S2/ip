package duke.commands;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
