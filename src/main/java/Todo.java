public class Todo extends Task {

    // Constructor
    public Todo(String description) {
        super(description);
    }

    // Return [T][ ] task
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
