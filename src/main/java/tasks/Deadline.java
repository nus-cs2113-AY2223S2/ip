package tasks;

public class Deadline extends Task{
    protected String by;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Deadline(String description, String doneBy) {
        super(description, "D");
        by = doneBy;
    }
    @Override
    public String toString() {
        return '[' + super.getType() + "]" + super.toString() + "(by: " + by + ')';
    }

}
