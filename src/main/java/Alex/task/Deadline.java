package Alex.task;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description,  String type, String by ) {
        super(description, type);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    public String getBy() {
        return by;
    }


}


