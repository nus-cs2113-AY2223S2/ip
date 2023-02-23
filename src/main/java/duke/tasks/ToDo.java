package duke.tasks;

public class ToDo extends Task {
    public static final String MARKER = "T";

    public ToDo(String description) {
        super(description, TaskEnum.TODO);
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER) + super.describe();
    }
}
