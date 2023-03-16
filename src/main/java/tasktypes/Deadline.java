package tasktypes;

public class Deadline extends Task {
    public static final String TYPE_ICON = "D";
    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getTypeIcon() {
        return TYPE_ICON;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String getTask() {
        return taskTypeIcon() + isDoneIcon() + " " + getDescription()
                + System.lineSeparator() + "    Deadline: " + getDueDate();
    }
}
