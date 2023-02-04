//initial skeleton adapted from  https://nus-cs2113-ay2223s2.github.io/website/schedule/week4/project.html partial solution
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}