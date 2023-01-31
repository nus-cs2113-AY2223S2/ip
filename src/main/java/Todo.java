public class Todo extends Task {
    protected boolean isDone;
    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    public void setDone(boolean status) {
        isDone = status;
    }

    public boolean isDone() {
        return isDone;
    }
}
