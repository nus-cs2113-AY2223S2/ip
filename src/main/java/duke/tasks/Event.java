package duke.tasks;
/**
 * Represents an Event task in Duke, which is a subclass of Task.
 */
public class Event extends Task {
    public String start;
    public String end;
    /**
     * Event constructor which calls the superclass constructor and initialises
     * start and end attributes of its own.
     *
     * @param name        Name of Event task
     * @param start       User specified start date of event
     * @param end         User specified end date of event
     * @param isCompleted Completion status of task
     */
    public Event(String name, String start, String end, Boolean isCompleted) {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String checkbox = "[ ]";
        if(isCompleted){
            checkbox = "[X]";
        }
        return "[E]" + checkbox + " " + name + "(from: " + start  + ", to: " + end + ")";
    }

    @Override
    public String toTextFileFormat(){
        return "event/" + name + "/" + isCompleted + "/" + start + "/" + end;
    }
}
