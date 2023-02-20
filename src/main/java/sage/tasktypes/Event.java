package sage.tasktypes;

/**
 * A class that represents an event task
 */

public class Event extends Task {

    private String startWhen = "";
    private String endWhen = "";

    public Event(String taskName, String startWhen, String endWhen) {
        super(taskName);
        this.startWhen = startWhen;
        this.endWhen = endWhen;
    }

    public String getStartWhen() {
        return startWhen;
    }

    public String getEndWhen() {
        return endWhen;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[E][X] " + super.getTaskDetails() + "(from: " + getStartWhen() + " to: " + getEndWhen() + ")";
        } else {
            return "[E][ ] " + super.getTaskDetails() + "(from: " + getStartWhen() + " to: " + getEndWhen() + ")";
        }
    }
}
