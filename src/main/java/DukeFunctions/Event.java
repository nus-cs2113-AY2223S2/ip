package DukeFunctions;

import Exceptions.DukeError;
import Exceptions.MissingInputException;

public class Event extends Todo {

    protected String from;
    protected String to;
    String deliverable;

    /**
     * Constructs an Event object with the specified input contents.
     *
     * @param inputContents The input contents of the event.
     * @throws MissingInputException If the input is missing the "/from" or "/to" keywords.
     */
    public Event(String inputContents) throws DukeError {
        super(inputContents);
        String[] parts = inputContents.split("/from|/to");


        if (parts.length < 3) {

            throw new DukeError("Missing inputs. Syntax for event: event <description> /from <time> /to <time>");
        } else {
            String deliverable = parts[0].trim();
            from = (parts.length > 1) ? parts[1].trim() : "";
            to = (parts.length > 2) ? parts[2].trim() : "";
        }
        this.deliverable = deliverable;
        this.type = "E";

    }

    /**
     * Returns the starting time of the event.
     *
     * @return String The starting time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the ending time of the event.
     *
     * @return String The ending time of the event.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event object.
     *
     * @return String A string representation of the event object.
     */
    @Override
    public String toString() {
        return deliverable + " (from: " + from + " to: " + to + ")";
    }

}
