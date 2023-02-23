package duke.task;

public class ToDo extends Task {

    public static final String TODO_LABEL = "T";

    /**
     * Initialises as in Task.
     *
     * @param description String describing the Task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[" + TODO_LABEL + "][" + getStatus() + "] " + description;
    }

}
