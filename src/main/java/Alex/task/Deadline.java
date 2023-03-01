package Alex.task;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description,  String type, String by ) {
        super(description, type);
        this.by = by;
    }

    /**
     * Updated standard printing format for Deadline task type
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    /**
     * get time to be completed
     */
    public String getBy() {
        return by;
    }


}


