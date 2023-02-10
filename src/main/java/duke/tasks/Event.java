package duke.tasks;

public class Event extends ToDo {

    protected String start;
    protected String end;

    /**
     * Constructor for Events class.
     *
     * @param taskName Task description.
     * @param start Start time of event.
     * @param end  End time of event.
     */
    public Event(String taskName, String start, String end) {
        super(taskName);
        super.type = "[E]";
        this.start = start;
        this.end = end;
    }

    public String getStart(){
        return this.start;
    }

    public String getEnd(){
        return this.end;
    }

    @Override
    public String toString(){
        return checkBoxOutput() + this.taskName + " (from: " + this.start + " to: "+ this.end + ")";
    }
}
