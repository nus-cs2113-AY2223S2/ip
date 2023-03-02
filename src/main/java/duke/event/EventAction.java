package duke.event;

import duke.item.Item;
import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.CommandAction;
import duke.utils.Constants;
import duke.utils.Message;
import duke.utils.MessageAction;

public class EventAction {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.ACCEPTABLE_DATE_TIME_FORMAT.toString());
    
    /**
     * Adds a Event item into the list.
     * 
     * @param parameters Gets the description and from/to of the item.
     * @param itemsSize Total size of the items list. Use for printing add message.
     * @return Item object (Subclass Event) which will be added into the list in .
     * @throws Exception when there are missing parameters, areValidParameters check fails, or Date from is after Date to.
     */
    public static Item addEvent(String parameters, int itemsSize) throws Exception {
        try {
            String[] attributes = parameters.split(" /from ", 2);
            String description = attributes[0];
            
            attributes = attributes[1].split(" /to ", 2);
            String fromString = attributes[0];
            String toString = attributes[1];

            CommandAction.areValidParameters(new String[] {description, fromString, toString});

            // Check if date from is after date to
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            if (from.isAfter(to)) {
                throw new DukeException();
            } 

            Item newEvent = new Event(description, from, to);

            MessageAction.printAddItemMessage(newEvent, itemsSize + 1);
            return newEvent;
        } catch (DukeException err) {
            throw new DukeException(Message.ERROR_EVENT_DATE_BEFORE.toString());
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_EVENT_MISSING_PARAMETER.toString());
        }
    }
}
