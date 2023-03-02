//parent class for ToDo, Deadline and Event class
abstract class Task {
    /** Representation of Task by their initial */
    protected final String label;
    /**  Completion status of a Task */
    protected final boolean isDone;
    /** Description of Task */
    protected final String taskDescription;

    Task(String label, boolean isDone, String taskDescription) {
        this.label = label;
        this.isDone = isDone;
        this.taskDescription = taskDescription;
    }

    /**
     * Returns a new Task that is marked
     */
    public abstract Task mark();

    /**
     * Returns a new Task that is unmarked
     */
    public abstract Task unmark();

    /**
     * Returns boolean that reflects if a Task contains a keyword
     *
     * @param keyword String to be verified if it can be found in a Task.
     * @return Boolean - If a Task has the keyword
     */
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

    /**
     * Formatted String composed of common fields used in the
     * toStringForDatabase() method for ToDo, Deadline and Event.
     *
     * @return String of common fields in a Task
     */
    public String CommonFieldsFor_toStringForDatabase() {
        return this.label + "," + this.isDone + "," + this.taskDescription;
    }

    public abstract String toStringForDatabase();

}