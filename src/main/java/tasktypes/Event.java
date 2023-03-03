package tasktypes;

public class Event extends Task{
    public static final String TYPE_ICON = "E";
    String startDate, endDate;
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String getTypeIcon() {
        return TYPE_ICON;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String getTask() {
        return taskTypeIcon() + isDoneIcon() + " " + getDescription() + System.lineSeparator()
                + "    Start: " + getStartDate() + System.lineSeparator()
                + "    End: " + getEndDate();
    }
}
