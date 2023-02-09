package duke.deadline;

import duke.item.Item;
import duke.exceptions.DukeException;
import duke.utils.CommandAction;
import duke.utils.Message;
import duke.utils.MessageAction;

public class DeadlineAction {
    /**
     * Adds a Deadline item into the list.
     * 
     * @param parameters Gets the description and deadline of the item.
     * @throws DukeException when there are missing parameters or areValidParameters check fails
     */
    public static Item addDeadline(String parameters, int itemsSize) throws Exception {
        try {
            String[] attributes = parameters.split(" /by ", 2);
            String description = attributes[0];
            String by = attributes[1];

            CommandAction.areValidParameters(new String[] {description, by});

            Item newDeadline = new Deadline(description, by);

            MessageAction.printAddItemMessage(newDeadline, itemsSize + 1);
            return newDeadline;
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_DEADLINE_MISSING_PARAMETER.toString());
        }
    }
}
