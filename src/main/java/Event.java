public class Event extends Task{
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        setStartEnd(start, end);
    }

    public void setStartEnd (String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String getTimings() {
        return this.start + "/" + this.end;
    }
}
