public class Deadline extends Task{
    protected String by;
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[D][" + getStatusIcon() + "] " + taskDescription + " (by:" + by + ")";
    }
}
