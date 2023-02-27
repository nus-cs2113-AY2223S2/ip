package max.task;


import max.Ui.Ui;

import javax.print.DocFlavor;

/**
 * Task is the parent class of all types of Tasks.
 * <p>
 * This abstract class describes common behaviour that all Tasks & its children  must inherit
 * A task should be able to minimally do the following <br>
 * 1. Store the description of the task
 * 2. Store the completion status of the task
 * 3. Return the description and metadata of the Task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private final Ui ui;
    private static final String TASK_LABEL = "?";
    private static final String STATUS_DONE = "X";
    private static final String STATUS_NOT_DONE = " ";

    /**
     * Based on the task, returns the label representing the Task type
     *
     * @return TaskLabel string
     */
    public String getTaskLabel() {
        return TASK_LABEL;
    }

    /**
     * Create a Task object
     *
     * @param description String describing the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.ui = new Ui();
    }

    public String getRawDescription() {
        return description;
    }

    /**
     * Get the Icon representing the status of the Task
     * <p>
     * Returns the constant Icon string STATUS_DONE or STATUS_NOT_DONE
     *
     * @return statusIcon
     */
    public String getStatusIcon() {
        return (isDone ? STATUS_DONE : STATUS_NOT_DONE); // mark done task with X
    }

    /**
     * Get a formatted string representing the task description for user viewing
     *
     * @return Formatted description string of the task
     */
    public String getDescription() {
        String wrappedIcon = ui.wrapMessage(getStatusIcon());
        return wrappedIcon + ui.getSingleSpace() + description;
    }

    /**
     * Find the status of Task's {@code isDone} status
     *
     * @return boolean {@code isDone} state
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Toggles the Task's {@code isDone} status as {@code true}
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Toggles the Task's {@code isDone} status as {@code false}
     */
    public void markAsUndone() {
        this.isDone = false;
    }

}
