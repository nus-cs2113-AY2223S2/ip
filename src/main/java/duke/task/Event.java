package duke.task;

public class Event extends Task {
    public String startTime;
    public String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() +
                "(from: " + getStartTime() + " to: " + getEndTime() + ")";
    }
}
