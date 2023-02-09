package duke.event;

import duke.item.Item;
import duke.exceptions.DukeException;
import duke.utils.CommandAction;
import duke.utils.Message;
import duke.utils.MessageAction;

public class EventAction {
    /**
     * Adds a Event item into the list.
     * 
     * @param parameters Gets the description and from/to of the item.
     * @throws DukeException when there are missing parameters or areValidParameters check fails
     */
    public static Item addEvent(String parameters, int itemsSize) throws Exception {
        try {
            String[] attributes = parameters.split(" /from ", 2);
            String description = attributes[0];
            
            attributes = attributes[1].split(" /to ", 2);
            String from = attributes[0];
            String to = attributes[1];

            CommandAction.areValidParameters(new String[] {description, from, to});

            Item newEvent = new Event(description, from, to);

            MessageAction.printAddItemMessage(newEvent, itemsSize + 1);
            return newEvent;
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_EVENT_MISSING_PARAMETER.toString());
        }
    }
}
