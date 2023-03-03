package duke.task;

/**
 * <h1>Deadline</h1>
 * The Deadline class is the child of the Task class.
 * It represents the Deadline tasks.
 * <p>
 *
 * @author  Tang Yinxuan (Sophie)
 * @version 1.0
 * @since   2023-03-03
 */
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
