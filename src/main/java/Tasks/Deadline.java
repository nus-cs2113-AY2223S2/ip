package Tasks;

public class Deadline extends Task{
    protected String type = "D";
    protected String by;

    public String getType() {
        return type;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Deadline(String description, String doneBy) {
        super(description);
        by = doneBy;
    }
    @Override
    public String toString() {
        return '[' + type + "]" + super.toString() + "(by:" + by + ')';
    }

}
