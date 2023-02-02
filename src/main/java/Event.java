public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String[] dates =  description.split("/");
        int fromLength = 4;
        String from  = dates[1].substring(0, fromLength);
        String fromDate = dates[1].substring(fromLength + 1);
        int toSize = 2;
        String to = dates[2].substring(0, toSize);
        String toDate = dates[2].substring(toSize + 1);
        return "[E]" + super.toString() + "(" + from + ": " + fromDate + to + ": " + toDate + ")" ;
    }
}
