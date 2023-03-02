package mom.todo;

import mom.command.CommandAction;
import mom.exceptions.MomException;
import mom.item.Item;
import mom.utils.Message;
import mom.utils.MessageAction;

public class TodoAction {
    /**
     * Adds a Todo item into the list.
     * 
     * @param parameters Gets the item description.
     * @param itemsSize Total size of the items list. Use for printing add message.
     * @return Item object (Subclass Todo) which will be added into the list in main.
     * @throws MomException when areValidParameters check fails.
     */
    public static Item addTodo(String parameters, int itemsSize) throws Exception {
        try {
            CommandAction.areValidParameters(new String[] {parameters});

            Item newTodo = new Todo(parameters);

            MessageAction.printAddItemMessage(newTodo, itemsSize + 1);
            return newTodo;
        } catch (Exception err) {
            throw new MomException(Message.ERROR_TODO_MISSING_PARAMETER.toString());
        }
    }
}
