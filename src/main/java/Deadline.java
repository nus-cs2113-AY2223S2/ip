public class Deadline extends Task {
    protected String deadline;

    /**
     * Constructor for Deadline task
     * @param taskName name of Deadline task
     * @param deadline deadline date of task
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Overrides toString() of Object class.
     * @return String indication of task type + whether task is done + task name.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
