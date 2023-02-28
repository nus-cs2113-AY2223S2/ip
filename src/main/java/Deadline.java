public class Deadline extends Todo {
    protected String by;

    /** Creates a deadline-type task, which contains a description and completion timing */
    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    /** Sets the completion timing of the task */
    public void setBy(String by) {
        this.by = by;
    }

    /** Returns type of task (deadline) */
    @Override
    public String getType() {
        return "deadline";
    }

    /** Returns completion timing of task */
    @Override
    public String getTimings() {
        return this.by;
    }

}