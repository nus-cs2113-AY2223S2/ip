abstract class Task {
    protected final String label;
    protected final boolean isDone;
    protected final String taskDescription;

    Task(String l, boolean id, String td) {
        this.label = l;
        this.isDone = id;
        this.taskDescription = td;
    }

    public abstract Task mark();

    public abstract Task unmark();

    @Override
    public String toString() {
        String formattedLabel = "[" + this.label + "]";
        String checkbox = "[X] ";
        if (!isDone) {
            checkbox = "[ ] ";
        }
        return formattedLabel + checkbox + this.taskDescription;
    }

}