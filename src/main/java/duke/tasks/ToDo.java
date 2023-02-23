package duke.tasks;

public class ToDo extends Task {
    public static final String MARKER = "T";

    public ToDo(String description) {
        super(description, TaskEnum.TODO);
    }

    /**
     * Describes the task with its task type, status and description.
     *
     * @return String describing the task
     */
    @Override
    public String describe() {
        return getCheckbox(true, MARKER) + super.describe();
    }
}
