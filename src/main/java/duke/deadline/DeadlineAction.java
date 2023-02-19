package duke.deadline;

import duke.item.Item;
import duke.exceptions.DukeException;
import duke.command.CommandAction;
import duke.utils.Message;
import duke.utils.MessageAction;

public class DeadlineAction {
    /**
     * Adds a Deadline item into the list.
     * 
     * @param parameters Gets the description and deadline of the item.
     * @param itemsSize Total size of the items list. Use for printing add message.
     * @return Item object (Subclass Deadline) which will be added into the list in main.
     * @throws Exception when there are missing parameters or areValidParameters check fails.
     */
    public static Item addDeadline(String parameters, int itemsSize) throws Exception {
        try {
            String[] attributes = parameters.split(" /by ", 2);
            String description = attributes[0];
            String datemark = attributes[1];

            CommandAction.areValidParameters(new String[] {description, datemark});

            Item newDeadline = new Deadline(description, datemark);

            MessageAction.printAddItemMessage(newDeadline, itemsSize + 1);
            return newDeadline;
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_DEADLINE_MISSING_PARAMETER.toString());
        }
    }
}
