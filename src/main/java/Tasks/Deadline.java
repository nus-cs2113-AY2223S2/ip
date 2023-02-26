package Tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName+" (by:" + by + ")", false, "[D]");
        this.by = by;
    }

    public String toString(){
        return super.toString();
    }

}