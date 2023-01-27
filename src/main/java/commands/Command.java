package commands;

public class Command {
    private String type;
    private String body;
    // Options
    private String from;
    private String to;
    private String by;

    public Command(String type, String body, String from, String to, String by) {
        this.type = type;
        this.body = body;
        this.from = from;
        this.to = to;
        this.by = by;
    }

    public String getType() {
        return type;
    }

    public String getBody() {
        return body;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("Command[type=%s, body=%s, from=%s, to=%s, by=%s]", type, body, from, to, by);
    }
}
