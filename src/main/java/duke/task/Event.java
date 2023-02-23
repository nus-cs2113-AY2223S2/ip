package duke.task;

/**
 * Task type that includes from time and to time
 */
public class Event extends Task{
    private String from;
    private String to;
    public Event(String name, String from, String to){
        super(name, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    public String getFrom(){
        return from;
    }

    public String getTo(){
        return to;
    }

    @Override
    public String toString(){
        return super.getTaskPrefixWithName() + " (from: " + from + " to: " + to + ")";
    }
}
