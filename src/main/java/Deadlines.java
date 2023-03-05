/**
 * A Deadlines task represents a task with a deadline.
 */
public class Deadlines extends Task {
    protected String deadline;
    public Deadlines(String description, String newDeadline) {
        super(description);
        deadline = newDeadline;
    }
    /**
     * This method is used to get the Deadlines icon.
     *
     * @return String This returns Deadlines icon.
     */
    public String getIcon() {
        return StrIntLib.deadlineIcon;
    }

    /**
     * This method is used to get the deadline of a Deadlines task.
     *
     * @return String This returns deadline of task.
     */
    public String getDeadline() {
        return deadline;
    }

}
