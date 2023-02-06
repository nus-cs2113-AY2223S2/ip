public class Event extends Task {
    protected String from;
    protected String to;

    public Event (String description, int taskID, String from, String to) {
        super(description, taskID);
        Task.taskCount += 1;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}