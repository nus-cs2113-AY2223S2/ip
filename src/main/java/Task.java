public class Task {
    protected final String description;
    protected final boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected String getDescription() {
        return this.description;
    }

    protected boolean getIsDone() {
        return this.isDone;
    }

    public Task setDone(boolean isDone) {
        return new Task(this.getDescription(), isDone);
    }

    @Override
    public String toString() {
        String boxContent = this.getIsDone() ? "X" : " ";
        return "[" + boxContent + "] " + this.getDescription();
    }
}
