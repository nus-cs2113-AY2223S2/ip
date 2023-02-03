public class Todo extends Task {
    protected final boolean isDone;

    public Todo(String description) {
        this(description, false);
    }
    private Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    protected boolean getIsDone() {
        return this.isDone;
    }

    public Todo setDone(boolean isDone) {
        return new Todo(this.getDescription(), isDone);
    }

    @Override
    public String toString() {
        String boxContent = this.getIsDone() ? "X" : " ";
        return "[" + boxContent + "] " + super.toString();
    }
}
