package max.task;

import max.ui.Ui;

/**
 * Deadline is a subclass of Task
 * <p>
 * It additionally stores the due date of the Task.
 */
public class Deadline extends Task {
    private static final String TASK_LABEL = "D";
    private static final String WRAPPER_BY = " (by: ";
    private static final String WRAPPER_END = ")";
    private Ui ui;
    private String dueDate;


    /**
     * Create a Deadline object, a subclass of Task.
     * Used to keep track of a Task's due date.
     *
     * @param description String describing the Deadline
     * @param dueDate     Date that the deadline is due
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
        ui = new Ui();
    }

    @Override
    public String getDescription() {
        String description = ui.wrapMessage(getTaskLabel()) + super.getDescription();
        description = description.concat(WRAPPER_BY + this.dueDate + WRAPPER_END);
        return description;
    }

    @Override
    public String getTaskLabel() {
        return TASK_LABEL;
    }

    /**
     * Helper method to retrieve the due date of a Deadline
     *
     * @return Deadline's due date
     */
    public String getDueDate() {
        return dueDate;
    }
}
