package duke.task;

public class Deadline extends Task {
    public String deadlineDate;

    /**
     * Initial constructor for Deadline task. Status is set to false (undone) by default.
     *
     * @param description description of deadline task content
     * @param deadlineDate date / time of the deadline of the task
     */
    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructor for Deadline task when the task status is given.
     *
     * @param description description of deadline test content
     * @param isDone status of the deadline task. True if done and false if not done.
     * @param deadlineDate date / time of the deadline of the task
     */
    public Deadline(String description, boolean isDone, String deadlineDate) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    public String getDeadline() {
        return this.deadlineDate;
    }

    /**
     * Returns Deadline task with all the details.
     *
     * @return Full deadline task in the format "[D][status symbol] task description (by: deadline date)"
     */
    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + getDeadline() + ")";
    }

}
