package chronos.tasktype;

import chronos.exceptions.ChronosExceptions;
import chronos.savehandler.*;

/**
 *
 */
public class Task {
    private boolean isDone;
    private String description;

    /**
     * Constructs a new Task Object
     *
     * @param isDone      The complete status of the task
     * @param description The details of the
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Creates a new Task Object
     *
     * @param description The details of the task
     * @throws IllegalArgumentException If the user does not provide any description,
     *                                  Chronos will prompt the user to do so
     */
    public Task(String description) {
        try {
            if (description == null) {
                throw new ChronosExceptions(description);
            }
            this.description = description;
            this.isDone = false;
        } catch (ChronosExceptions error) {
            System.out.println("THIS CANNOT BE EMPTY. REMOVE THIS TASK AND ENTER 'help' TO VIEW PROPER FORMAT");
        }
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a character symbol to represent the completion status of a task
     * If the task is marked as done, the symbol will be a checked box ('√');
     * * otherwise, the symbol will be an empty box ('□').
     *
     * @return a character symbol representing the completion status of a task
     */
    public char setCheckMark() {
        char icon = '□';
        if (this.isDone == true) {
            icon = '√';
        }
        return icon;
    }

    /**
     * Toggles the completion status of a task, if the task is currently marked
     * as done, it will be marked as not done; if it's not done, it will be
     * marked as done.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns a string representation of a task, including its completion status
     * (represented by a checkmark symbol) and its description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("%c %s", setCheckMark(), getDescription());
    }

    /**
     * Creates a new instance of the {@code Save} class, using the current task's
     * properties (description and completion status) and the specified task type.
     *
     * @param taskType the type of task to be saved (e.g. "todo", "deadline", "event")
     * @return a new instance of the {@code Save} class representing the current task
     */
    public Save toSave(String taskType) {
        return new Save(taskType, isDone, description, "", "", "");
    }
}
