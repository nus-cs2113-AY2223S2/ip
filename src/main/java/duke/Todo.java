package duke;

public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
    public String toStorage() {
        return "[T]" + super.toStorage();
    }
}
