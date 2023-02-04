public class DeadLine extends Task {

    private String by;

    public DeadLine(String description, String by) {

        super(description);
        setBy(by);

    }

    public String getBy() {

        return by;

    }
    public void setBy(String by) {

        this.by = by;

    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + "(by: " + getBy() + ")";

    }

}
