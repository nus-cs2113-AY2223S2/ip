package bro.tasks;

public class Event extends Task{
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.setType("E");
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Event(String name, boolean completed, String startTime, String endTime) {
        super(name, completed);
        this.setType("E");
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}