package duke.task;

public class Events extends ToDo {

    protected String start;
    protected String end;

    /**
     * Constructor for Events class
     *
     * @param taskName Task description
     * @param start Start time of event
     * @param end  End time of event
     */
    public Events(String taskName, String start, String end) {
        super(taskName);
        super.type = "[E]";
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString(){
        return checkBoxOutput() + this.taskName + " (from: " + this.start + " to: "+ this.end + ")";
    }
}
