package task;

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

    @Override
    public String getFileWriteFormat() {
        // 1 if done, 0 if not done
        String done = isDone ? "1" : "0";
        String output = String.format("T | ");
        return output;
    }
}
