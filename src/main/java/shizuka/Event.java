package shizuka;

public class Event extends Todo {
    private String startDate, endDate;

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
}
