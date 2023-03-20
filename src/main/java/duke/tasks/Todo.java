package duke.tasks;//package Duke.java;

public class Todo extends Task {
    public Todo(String description) {

        super(description);
    }

    @Override
    public String getTask() {
        return String.format("[T]" + super.getTask());
    }
}