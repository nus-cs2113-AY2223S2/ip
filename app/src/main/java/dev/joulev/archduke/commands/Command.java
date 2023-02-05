package dev.joulev.archduke.commands;

/**
 * This class represents a command that the user has inputted. It contains the
 * main command type, the body and the supported options ({@code /from},
 * {@code /to} and {@code /by}), all in the form of strings.
 */
public class Command {
    /** The main command type. Guaranteeed to be non-null. */
    private String type;
    /** The body of the command. Can be null. */
    private String body;
    /** The value of option {@code /from}, if any. */
    private String from;
    /** The value of option {@code /to}, if any. */
    private String to;
    /** The value of option {@code /by}, if any. */
    private String by;

    /**
     * Constructs a new {@link Command} object.
     * 
     * @param type The main command type
     * @param body The body of the command, may be null
     * @param from The value of option {@code /from}, may be null
     * @param to   The value of option {@code /to}, may be null
     * @param by   The value of option {@code /by}, may be null
     */
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
        return String.format("Command[type=%s, body=%s, from=%s, to=%s, by=%s]", type, body, from,
                to, by);
    }
}
