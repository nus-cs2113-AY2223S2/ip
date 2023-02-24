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

    public String toFileFormat() {
        return description;
    }
}
