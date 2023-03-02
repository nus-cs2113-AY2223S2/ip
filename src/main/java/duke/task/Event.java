package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents task that has both start time and end time.
 */
public class Event extends Task {
    
    protected String from;
    protected String to;
    protected LocalDateTime localFrom;
    protected LocalDateTime localTo;

    /**
     * Converter for <code>Event</code> task. It accepts an input in format of an instruction and
     * returns the <code>Event</code> task object.
     * @param instruction Written in format of "event <code>description</code> /from <code>from</code>(start time) /to <code>to</code>(end time)", 
     * e.g. "event CS2113 class /from 2023/03/03 16:00 /to 2023/03/03 18:00".
     * @return <code>Event</code> object constructed from input <code>instruction</code>.
     * @throws DukeException If 1) description is not given; 2) start time is not given; 3) end time is not given;
     * 4) start time is in wrong format; 5) end time is in wrong format.
     */
    public static Event toEvent(String instruction) throws DukeException {
        int contentIdx = instruction.indexOf("/from");
        int fromIdx = instruction.indexOf("/to");

        if(contentIdx == -1) {
            throw new DukeException("Please add event's start time.\n" + "    " +
                                    "(Event format: [Event Content] /from [Start Time] /to [End Time])");
        }else if(fromIdx == -1) {
            throw new DukeException("Please add event's end time.\n" + "    " +
                                    "(Event format: [Event Content] /from [Start Time] /to [End Time])");
        }

        String eventContent = instruction.substring(0, contentIdx);

        if(eventContent.trim().equals("")) {
            throw new DukeException("The content of an event cannot be empty.");
        }

        String eventFrom = instruction.substring(contentIdx + "/from ".length(), fromIdx);

        if(eventFrom.trim().equals("")) {
            throw new DukeException("The start time of an event cannot be empty.");
        }

        String eventTo = instruction.substring(fromIdx + "/to ".length());
        
        if(eventTo.trim().equals("")) {
            throw new DukeException("The end time of an event cannot be empty.");
        }
        
        return new Event(eventContent, eventFrom, eventTo);
    }

    /**
     * Constructor for <code>Event</code> task.
     * @param description Description of <code>Event</code> task, e.g. "CS2113 class".
     * @param from Start time of <code>Event</code> task, e.g. "2023/03/03 16:00".
     * @param to End time of <code>Event</code> task, e.g. "2023/03/03 18:00".
     */
    public Event(String description, String from, String to) {
        super(description, 'E');
        this.from = from;
        this.to = to;
        convertDateTime(from, to);
    }

    /**
     * Convert start time and end time from <code>String</code> type to <code>LocalDateTime</code> type.
     * If <code>from</code> and/or <code>to</code> is not in the format of "yyyy/MM/dd HH:mm", converter will not work and
     * set the result as <code>null</code> both.
     * @param from Start time of the task in <code>String</code> type.
     * @param to End time og the task in <code>String</code> type.
     */
    private void convertDateTime(String from, String to) {
        try {
            from = from.substring(0, from.length() - 1);
            localFrom = LocalDateTime.parse(from, parseFormatter);
            localTo   = LocalDateTime.parse(to  , parseFormatter);
        } catch(DateTimeParseException e) {
            localFrom = null;
            localTo   = null;
        }
    }

    @Override
    public String getTimeBound() {
        return from + "-" + to;
    }

    @Override
    public String toString() {
        if (localFrom == null || localTo == null) {
            return super.toString() + "(from: " + from + "to: " + to + ")";
        } else {            
            return super.toString() + "(from: " + localFrom.format(printFormatter) + " to: " + localTo.format(printFormatter) + ")";
        }
    }

    @Override
    public boolean haveValidDate() {
        return (localFrom != null) && (localTo != null);
    }

    @Override
    public LocalDateTime getEndTime() {
        return localTo;
    }
}
