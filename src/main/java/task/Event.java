package task;

public class Event extends Task {
    String from,to;
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString()
                + "(" + "from " + from + " to " + to + ")";
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
}
