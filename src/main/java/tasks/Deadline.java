package tasks;

public class Deadline extends Task {

    protected String by;
    public Deadline(String taskName, String by) {
        super(taskName, false, "[D]");
        this.by = by;
    }

    public String toString(){
        return taskSign + super.toString() + " (by:" + by + ")";
    }

} // tasks.Deadline class ends here