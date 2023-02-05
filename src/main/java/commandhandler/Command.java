package commandhandler;

public class Command {
    private String action;
    private String details;
    private String start;
    private String end;
    private String due;

    public Command(String action, String details, String start, String end, String time) {
        this.action = action;
        this.details = details;
        this.start = start;
        this.end = end;
        this.due = time;
    }

    public String getAction() {
        return action;
    }

    public String getDetails() {
        return details;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getDue() {
        return due;
    }
    @Override
    public String toString() {
        return String.format("Command[type=%s, body=%s, start=%s, end=%s, due=%s]", action, details, start, end, due);
    }

}
