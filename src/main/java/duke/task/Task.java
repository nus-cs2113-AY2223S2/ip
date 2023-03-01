package duke.task;

public class Task {
    private final static String MARKED_ICON = "[/]";
    private final static String UNMARKED_ICON = "[ ]";
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /* get status icon for a task to represent whether it is marked */
    public String getStatusIcon() {
        String statusIcon = isMarked ? MARKED_ICON : UNMARKED_ICON;
        return statusIcon;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    /* change data to a file format in order to store in a file */
    public String toFileFormat() {
        return description;
    }
}
