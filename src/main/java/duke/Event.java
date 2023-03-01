package duke;
/**
* Event type of task. Has a start and end date.
 */
public class Event extends Todo{
    protected String start;
    protected String end;
    public Event(String description, String start, String end)
    {
        super(description);
        this.start = start;
        this.end = end;
    }
    /**
     * Returns icon matching event type
     * @return icon of type event
     */
    public String getTypeIcon() {
        return ("[E]"); // mark done task with X
    }
    /**
     * Returns icon matching status type
     * @return icon that depicts status of done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    @Override
    public String getInfo() {
        return start + "," + end;
    }
}
