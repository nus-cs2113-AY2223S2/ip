package duke.tasks;

public class Deadline extends ToDo {

    protected String deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param taskName Task description.
     * @param deadline Deadline of task.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        super.type = "[D]";
        this.deadline = deadline;
    }

    /**
     * Getter for deadline field.
     *
     * @return the deadline of the task.
     */
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return checkBoxOutput() + this.taskName + " (by: " + this.deadline + ")";
    }
}
