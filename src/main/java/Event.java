public class Event extends Deadline{
    private final String start;
    public Event(String taskName, int index, String start, String deadline) {
        super(taskName, index, deadline);
        this.start = start;
    }
    public String getStart() {
        return this.start;
    }
}
