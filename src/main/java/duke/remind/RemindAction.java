package duke.remind;

import duke.item.Item;
import duke.exceptions.DukeException;
import duke.utils.CommandAction;
import duke.utils.Message;
import duke.utils.MessageAction;

public class RemindAction {
    /**
     * Adds a Todo item into the list.
     * 
     * @param parameters Gets the item description
     * @throws DukeException when areValidParameters check fails
     */
    public static Item addRemind(String parameters, int itemsSize) throws Exception {
        try {
            CommandAction.areValidParameters(new String[] {parameters});

            Item newTodo = new Remind(parameters);

            MessageAction.printAddItemMessage(newTodo, itemsSize + 1);
            return newTodo;
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_REMIND_MISSING_PARAMETER.toString());
        }
    }
}
