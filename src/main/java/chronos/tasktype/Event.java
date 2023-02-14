package chronos.tasktype;

import chronos.savehandler.Save;

//more inheritance
public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        if (start == null || end == null) {
            throw new IllegalArgumentException("Please provide BOTH start and end times.");
        }
        this.end = end;
        this.start = start;
    }
    public Event(String description, String start, String end, boolean isDone) {
        super(isDone, description);
        if (start == null || end == null) {
            throw new IllegalArgumentException("Please provide BOTH start and end times.");
        }
        this.end = end;
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
    @Override
    public String toString() {
        return String.format("[E] %s (start: %s end: %s)", super.toString(), getStart(), getEnd());
    }
    @Override
    public Save toSave(String taskType) {
        return new Save(taskType, isDone(), getDescription(), "", start, end);
    }

}
