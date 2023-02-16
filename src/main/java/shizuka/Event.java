package shizuka;

public class Event extends Todo {
    public static final String ITEM_SEPARATOR = " | ";
    private final String startDate;
    private final String endDate;

    public Event(String[] args) {
        super(args[0]);
        this.taskType = "E";
        this.startDate = args[1];
        this.endDate = args[2];
    }
    public String printTask(){
        return this.getTaskIcon() + this.getStatusIcon() + ' ' + this.description
                + " (from: " + startDate + " to: " + endDate + ")";
    }

    public String saveTask(){
        return this.taskType + ITEM_SEPARATOR + (this.isDone ? "1" : "0") + ITEM_SEPARATOR + this.description
                + ITEM_SEPARATOR + this.startDate + ITEM_SEPARATOR + this.endDate;
    }
}
