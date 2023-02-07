public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String printTask() {
        return super.printTask() + "(from: " + from + " to: " + to + ")";
    }
    
}
