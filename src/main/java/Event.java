public class Event extends Todo{
    protected String start;
    protected String end;
    public Event(String description, String start, String end)
    {
        super(description);
        this.start = start;
        this.end = end;
    }
    public String getTypeIcon() {
        return ("[E]"); // mark done task with X
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
}
