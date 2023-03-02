public class Event extends Deadline{
    private final String start;
    public Event(String taskName, boolean isDone, int index, String start, String deadline) {
        super(taskName, isDone, index, deadline);
        this.start = start;
    }
    public String getStart() {
        return this.start;
    }
}
