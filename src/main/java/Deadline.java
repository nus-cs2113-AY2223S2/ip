public class Deadline extends Task {
    protected String by;
    public Deadline(String TaskName, String by) {
        super(TaskName);
        this.by = by;
    }

    public String toString(){
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

} // Deadline class ends here
