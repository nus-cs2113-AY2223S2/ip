package duke.tasklist.task;

import java.util.concurrent.ExecutionException;

/**
 * tasks that start at a specific date/time and ends at a specific date/time.
 * e.g., (a) team project meeting 2/10/2019 2-4pm.
 *      (b) orientation week 4/10/2019 to 11/10/2019.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor initializing the content, start time, end time of the Event task.
     * The task is unmarked by default.
     * @param content content of the Event.
     * @param from starting time of the Event.
     * @param to ending time of the Event.
     */
    public Event(String content, String from, String to) {
        this(content, from, to, false);
    }

    public Event(String content, String from, String to, boolean isMarked) {
        super(content, isMarked);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event class from input arguments.
     * @param args arguments containing content, from, to time of the event.
     * @throws IllegalArgumentException exceptions with message when (part of) input is missing.
     */
    public Event(String[] args) throws IllegalArgumentException {
        assert args[0].equals("event");
        StringBuilder content = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("/from")) {
                fromIndex = i;
            } else if (args[i].equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException("☹ OOPS!!! Cannot find from or to time of the event!");
        }

        for (int i = 1; i < args.length; ++i) {
            if (i < fromIndex) {
                content.append(args[i]).append(" ");
            } else if (i > fromIndex && i < toIndex) {
                from.append(args[i]).append(" ");
            } else if (i > toIndex) {
                to.append(args[i]).append(" ");
            }
        }

        if (content.toString().isEmpty() || from.toString().isEmpty() || to.toString().isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The content/from/to of an event cannot be empty!");
        }

        this.content = content.toString().trim();
        this.from = from.toString().trim();
        this.to = to.toString().trim();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String toCSV() {
        return "event," +
                super.toCSV() + "," +
                "\"" + from + "\"" + "," +
                "\"" + to + "\"";
    }

    /**
     * Converts the task to a string with label, marked status, starting and ending time.
     * @return a string containing the task's label, marked status, starting and ending time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return super.equals(obj)
                    && (obj instanceof Event)
                    && (((Event) obj).from.equals(this.from))
                    && (((Event) obj).to.equals(this.to));
        }
    }
}
