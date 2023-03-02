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
     * @throws Exception when there are missing parameters or areValidParameters check fails.
     */
    public static Item addEvent(String parameters, int itemsSize) throws Exception {
        try {
            String[] attributes = parameters.split(" /from ", 2);
            String description = attributes[0];
            
            attributes = attributes[1].split(" /to ", 2);
            String from = attributes[0];
            String to = attributes[1];

            CommandAction.areValidParameters(new String[] {description, from, to});

            Item newEvent = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));

            MessageAction.printAddItemMessage(newEvent, itemsSize + 1);
            return newEvent;
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_EVENT_MISSING_PARAMETER.toString());
        }
    }
}
