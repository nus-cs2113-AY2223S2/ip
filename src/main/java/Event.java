public class Event extends Task{
    protected String startDate;
    protected String endDate;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString(){
        return "[E]" + super.toString() + " (From: " + this.getStartDate() + " to: " + this.getEndDate() + ")";
    }
}
