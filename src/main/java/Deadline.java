public class Deadline extends Task{

    private String by;
    public Deadline(String name, Boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }


    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }


    public String toString() {
        return "[D]" + super.toString() + " (" + "by: " + by + ")";
    }
}
