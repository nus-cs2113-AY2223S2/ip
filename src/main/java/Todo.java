public class Todo extends Task {
    private static final String TYPE = "T";
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

    protected String getType() {
        return Todo.TYPE;
    }

    public Todo setDone(boolean isDone) {
        return new Todo(this.getDescription(), isDone);
    }

    @Override
    public String toString() {
        String boxContent = this.getIsDone() ? "X" : " ";
        return String.format("[%s][%s] %s", this.getType(), boxContent, super.toString());
    }
}
