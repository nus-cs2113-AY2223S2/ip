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

    public String toString() {
        if (super.IsCompleted()) {
            return ".[E][X] " + super.GetTaskName() + "(from: " + getStartWhen() + " to: " + getEndWhen() + ")";
        } else {
            return ".[E][ ] " + super.GetTaskName() + "(from: " + getStartWhen() + " to: " + getEndWhen() + ")";
        }
    }
}
