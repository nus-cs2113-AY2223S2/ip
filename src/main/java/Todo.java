public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String fullDescription() {
        String full;
        full = (isDone ? "[T][X] " : "[T][ ] ") + this.description;
        return full;
    }
}
