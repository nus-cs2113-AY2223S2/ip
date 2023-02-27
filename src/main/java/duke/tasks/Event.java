package duke.tasks;

public class Event extends Task {
    protected String start;
    protected String end;
    protected String type = "event";
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public void printTask() {
        System.out.println("[E][" + getStatusIcon() + "] " + description + "(from:" + start + "to:" + end + ")");
    }
}