package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveTaskString() {
        String saveString = new String();
        saveString += "T" + COMMA + isDone + COMMA + description;
        return saveString;
    }
}
