public class Todo extends Task {

    // Constructor
    public Todo(String description, int taskNumber) {
        super(description, taskNumber);
    }

    // Return [T][ ] task
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
