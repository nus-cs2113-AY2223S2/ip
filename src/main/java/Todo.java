//package Duke.java;

public class Todo extends Task {
    public Todo(String description) {

        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}