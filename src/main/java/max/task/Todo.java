package max.task;

import max.ui.Ui;

/**
 * Todo is a subclass of Task
 * <p>
 * It stores the description of the task.
 */
public class Todo extends Task {
    private static final String TASK_LABEL = "T";
    private final Ui ui;

    /**
     * Create an Todo object, a subclass of Task.
     *
     * @param description String describing the Task
     */
    public Todo(String description) {
        super(description);
        ui = new Ui();
    }

    @Override
    public String getDescription() {
        String wrappedTask = ui.wrapMessage(getTaskLabel());
        return wrappedTask + super.getDescription();
    }

    @Override
    public String getTaskLabel() {
        return TASK_LABEL;
    }
}
