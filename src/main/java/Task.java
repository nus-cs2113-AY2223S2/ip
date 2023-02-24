abstract class Task {
    protected final String label;
    protected final boolean isDone;
    protected final String taskDescription;

    Task(String label, boolean isDone, String taskDescription) {
        this.label = label;
        this.isDone = isDone;
        this.taskDescription = taskDescription;
    }

    public abstract Task mark();

    public abstract Task unmark();

    public boolean containsKeyword(String keyword) {
        return this.toString().contains(keyword);
    }

    @Override
    public String toString() {
        String formattedLabel = "[" + this.label + "]";
        String checkbox = "[X]";
        if (!isDone) {
            checkbox = "[ ]";
        }
        return formattedLabel + checkbox + " " + this.taskDescription;
    }

    public String CommonFieldsFor_toStringForDatabase() {
        return this.label + "," + this.isDone + "," + this.taskDescription;
    }
    public abstract String toStringForDatabase();

}