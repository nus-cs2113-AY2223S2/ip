/**
 * Represents tasks that need to be done before a specific date/time.
 * e.g., submit report by 11/10/2019 5pm.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor initializing the content and deadline of the Deadline task.
     * The task is unmarked by default.
     * @param content content of the Deadline task.
     * @param by deadline of the Deadline task.
     */
    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    /**
     * Constructs a Deadline class from input arguments.
     * @param args input arguments containing content and by time of the deadline.
     */
    public Deadline(String[] args) {
        assert args[0].equals("deadline");
        StringBuilder content = new StringBuilder();
        StringBuilder by = new StringBuilder();

        int byIndex = -1;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("/by")) {
                byIndex = i;
            }
        }

        if (byIndex == -1) {
            throw new IllegalArgumentException("Cannot find by time!");
        }

        for (int i = 1; i < args.length; ++i) {
            if (i == byIndex) {
                continue;
            }
            if (i < byIndex) {
                content.append(args[i]).append(" ");
            } else {
                by.append(args[i]).append(" ");
            }
        }

        this.content = content.toString().trim();
        this.by = by.toString().trim();
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Converts the task to a string with label, mark status and deadline.
     * @return a string containing the task's label, mark status and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
