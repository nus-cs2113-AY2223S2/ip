/*
 * Sub-class of super-class <code>Task</code>, represents a task that is an event,
 * Contains attributes event name, start and end time
 */
public class Event extends Task {
    public String startTime, endTime, eventName;
    public String[] info;

    public Event(String description) {
        super(description);
        this.info = this.description.split("/from", 2);
        this.eventName = info[0];
        this.startTime = info[1].split("/to", 2)[0];
        this.endTime = info[1].split("/to", 2)[1];
    }

    @Override
    public String fileFormat() {
        return (String.format("E|%b|%s /from %s /to %s", super.isDone, this.eventName, this.startTime, this.endTime));
    }

    @Override
    public String toString() {
        return ("[E][" + super.getStatusIcon() + "] " + this.eventName) +
                " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
