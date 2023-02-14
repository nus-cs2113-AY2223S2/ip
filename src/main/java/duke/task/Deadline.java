package duke.task;

public class Deadline extends Task {

    // Solution below adapted from https://nus-cs2113-ay2223s2.github.io/website/admin/ip-w4.html
    // Week 4 A-Inheritance partial solution with modifications
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }
    public String getBy() {
        return this.by;
    }
    public void setBy(String by) {
        this.by = by;
    }
    @Override
    public String getDescription() {
        return "[D] " + super.getDescription() + "(by: " + this.getBy() + ")";
    }
}
