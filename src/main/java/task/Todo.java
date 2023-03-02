package task;

public class Todo extends Task {

    // Constructor
    public Todo(String description, int taskNumber) {
        super(description);
    }

    // Return [T][ ] task
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public String getFileWriteFormat() {
        String output = "T " + super.getFileWriteFormat();
        return output;
    }
}
