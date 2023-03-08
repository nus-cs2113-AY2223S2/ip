package grandduke.task;

public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a new Deadline object
     * @param taskDesc the description of the task
     * @param deadline the deadline of the task
     */
    public Deadline(String taskDesc, String deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the Deadline object
     */
    @Override
    public String getTaskPrint() {
        return "[D]" + super.getTaskPrint() + " (by: " + deadline + ")";
    }

    /**
     * Returns the string representation of the Deadline object to be saved in the
     * data file
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns the string representation of the Deadline object to be saved in the
     */
    @Override
    public String getTaskSaveString() {
        return "deadline | " + (super.getIsDone() ? "1" : "0") + " | " + super.getTaskDesc() + " /by " + deadline;
    }
}
