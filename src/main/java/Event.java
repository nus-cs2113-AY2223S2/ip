public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String[] command =  description.split("/");
        String from  = command[1].substring(0, 4);
        String fromDate = command[1].substring(5);
        String to = command[2].substring(0, 2);
        String toDate = command[2].substring(3);
        return "[E]" + super.toString() + "(" + from + ": " + fromDate + to + ": " + toDate + ")" ;
    }
}
