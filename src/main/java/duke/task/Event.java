package duke.task;

import duke.DukeException;

public class Event extends Task {
    
    protected String from;
    protected String to;

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
    }

    @Override
    public String getTimeBound() {
        return from + "-" + to;
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
