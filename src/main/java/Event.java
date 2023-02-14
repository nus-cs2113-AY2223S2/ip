public class Event extends Task {

    protected String description;
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }
    public String getTo(){
        return this.to;
    }
    public String getFrom(){
        return this.from;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public void setTo(String to){
        this.to = to;
    }

    @Override
    public String getDescription() {
        return "[E] " + super.getDescription() + "(from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
}
