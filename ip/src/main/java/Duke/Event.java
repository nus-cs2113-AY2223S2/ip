package Duke;

public class Event extends Task {
    String startDate;
    String endDate;

    public Event (String description, String startDate, String EndDate) {
        super (description);
        this.startDate = startDate;
        this.endDate = EndDate;
        this.type = "[E]";
    }

    @Override
    public String toString () {
        return super.toString () + "(from:" + startDate + "to:" + endDate + ")";
    }
}