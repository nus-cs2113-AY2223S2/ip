package duke.task;

public class Event extends Tasks {
    String startDate;
    String endDate;
    public Event(String item, boolean isMarked, String startDate, String endDate) {
        super(item, isMarked);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + startDate + " To: " + endDate + ")";
    }
}
