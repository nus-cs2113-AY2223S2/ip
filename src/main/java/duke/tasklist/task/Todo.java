package duke.tasklist.task;

public class Todo extends Task {
    private static final String TODO_ICON = "T";

    /**
     * Initialises an instance of To do task.
     * Stores a task name into the instance of To do.
     *
     * @param taskName Name of the to do task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns to do task formatted as a string for printing to CLI.
     *
     * @return String format of to do task for printing to CLI.
     */
    @Override
    public String toString() {
        return "["+ TODO_ICON + "]" + super.toString();
    }

    /**
     * Returns to do task formatted as a string for saving to duke.txt.
     *
     * @return String format of to do task for saving to duke.txt.
     */
    @Override
    public String toFile() {
        return TODO_ICON + " " + super.toFile();
    }
}
