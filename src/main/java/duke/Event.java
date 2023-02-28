package duke;
import duke.exceptions.EventException;
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) throws EventException {
        super(description, isDone);
        this.from = from;
        this.to = to;
        if(to.length() == 0){
            throw new EventException();
        }
    }

    public String getType(){
        return "E";
    }

    public String getPeriod(){
        return " (from: " + from + " to: " + to + ")";
    }

    public String getPeriodSave(){
        return  from + " | " + to;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + "  [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}