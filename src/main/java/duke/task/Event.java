package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Event extends Task {
    
    protected String from;
    protected String to;
    protected LocalDateTime localFrom;
    protected LocalDateTime localTo;

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

    public Event(String description, String from, String to) {
        super(description, 'E');
        this.from = from;
        this.to = to;
        convertDateTime(from, to);
    }

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
