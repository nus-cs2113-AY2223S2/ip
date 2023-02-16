package shizuka;

public class Event extends Todo {
    public static final String ITEM_SEPARATOR = " / ";
    private final String startDate;
    private final String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.taskType = "E";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String[] args) {
        super(args[2]);
        this.isDone = args[1].equals("1");
        this.taskType = "E";
        this.startDate = args[3];
        this.endDate = args[4];
    }

    public String printTask() {
        return this.getTaskIcon() + this.getStatusIcon() + ' ' + this.description
                + " (from: " + startDate + " to: " + endDate + ")";
    }

    public String saveTask() {
        return this.taskType + ITEM_SEPARATOR + (this.isDone ? "1" : "0") + ITEM_SEPARATOR + this.description
                + ITEM_SEPARATOR + this.startDate + ITEM_SEPARATOR + this.endDate + System.lineSeparator();
    }
}
