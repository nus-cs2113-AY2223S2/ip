package duke.tasklist.task;

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
        this(content, by, false);
    }

    public Deadline(String content, String by, boolean isMarked) {
        super(content, isMarked);
        this.by = by;
    }

    /**
     * Constructs a Deadline class from input arguments.
     * @param args input arguments containing content and by time of the deadline.
     * @throws IllegalArgumentException exceptions with message when (part of) input is missing.
     */
    public Deadline(String[] args) throws IllegalArgumentException {
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
            throw new IllegalArgumentException("☹ OOPS!!! Cannot find the by time of the event.");
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

        if (content.toString().isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (by.toString().isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The by time of a deadline cannot be empty.");
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


    public String toCSV() {
        return "deadline," +
                super.toCSV() + "," +
                "\"" + by + "\"";
    }
    /**
     * Converts the task to a string with label, mark status and deadline.
     * @return a string containing the task's label, mark status and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return super.equals(obj)
                    && (obj instanceof Deadline)
                    && (((Deadline) obj).by.equals(this.by));
        }
    }
}
