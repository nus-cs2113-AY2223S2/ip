public class Todo extends Task {
    public Todo(String description, boolean isMark) {
        super(description, isMark);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}