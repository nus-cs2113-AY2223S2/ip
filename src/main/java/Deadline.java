public class Deadline extends Task{
    protected String by;

    public Deadline(int Index, String description, String by) {
        super(Index,description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return super.toString() + "[D]" + "[" + status + "]" + getDescription() + "(by: " + getBy() + ")";
    }
}
