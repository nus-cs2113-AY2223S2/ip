public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String[] dates =  description.split("/from | /to");
        String fromDate = dates[1];
        String toDate = dates[2];
        return "[E]" + super.toString() + "(from: " + fromDate + " to:" + toDate + ")" ;
    }
}
