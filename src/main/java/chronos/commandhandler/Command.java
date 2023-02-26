package chronos.commandhandler;

/**
 * Represents all user commands, which is broken down into action, details of task added. In the case of an 'event'
 * the user command will contain the respective start and end times/date. In the case of a 'deadline, the user command
 * will contain the due date/time
 */
public class Command {

    private static final String EXAMPLE = "event EE2026 midterms /start 4 March 3 pm /end 4 March 4.30pm";
    private String action; //the type of task or command
    private String details; //the accompanying details after the user has defined the action
    private String start; //for an event, if applicable
    private String end; // for an event, if applicable
    private String due; //for a deadline, if applicable

    /**
     * Constructs a Command object with the given action, details, start time, end time, and due time.
     * This will be parsed in InputParser
     *
     * @param action  The action of the user command
     * @param details The details of the user command
     * @param start   The start time of the user command in the case of an event type task
     * @param end     The end time of the command in the case of an event type task
     * @param time    The due time of the command in the case of a deadline type task
     */
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
