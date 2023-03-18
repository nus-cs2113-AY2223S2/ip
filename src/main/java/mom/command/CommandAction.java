package mom.command;

import java.util.ArrayList;

import mom.deadline.DeadlineAction;
import mom.event.EventAction;
import mom.exceptions.MomException;
import mom.item.Item;
import mom.item.ItemAction;
import mom.todo.TodoAction;
import mom.utils.Message;
import mom.utils.MessageAction;

public class CommandAction {
    /**
     * Retrieve the command from the user's entered input.
     * Invalid command will return an error.
     * 
     * @param input the user inputted text.
     * @return command specified by the user.
     * @throws MomException when an invalid command is entered.
     */
    public static Command getCommand(String input) throws MomException {
        try {
            String[] splitInput = input.split(" ", 2);
            Command command = Command.valueOf(splitInput[0].toUpperCase());
            return command;
        } catch (IllegalArgumentException err) {
            throw new MomException(Message.ERROR_INVALID_COMMAND.toString());
        }
    }

    /**
     * Retrieve the parameters from the user's entered input.
     * If there is no parameters specified, return empty string.
     * 
     * @param input the user inputted text.
     * @return parameters specified by the user.
     */
    public static String getParameters(String input) {
        if (input.contains(" ")) {
            return input.split(" ", 2)[1];
        }

        return "";
    }

    /**
     * Checks for empty/whitespace parameters input by the user.
     * Throws an IllegalArgumentException if check fails.
     * 
     * @param input the user inputted text.
     * @return parameters specified by the user.
     * @throws IllegalArgumentException when check fails.
     */
    public static void areValidParameters(String[] parameters) throws IllegalArgumentException {
        for (String parameter : parameters) {
            if (parameter.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Gets the command and parameters from Main class and executes it.
     * Every command (except exit) will return a '0' integer status to indicate ready for next input.
     * Exit command will return '-1' which will terminate Duke.
     * 
     * @param command Indicates the type of action.
     * @param parameters Arguments for some actions e.g. mark.
     * @param items List of items which will require for some actions.
     * @return status where 0 for OK, -1 for exit.
     * @throws Exception when command is invalid, or error from an action.
     */
    public static int executeCommand(Command command, String parameters, ArrayList<Item> items) throws Exception {
        switch(command) {
        case LIST:
            printList(items);
            break;
        case MARK:
            markItem(items, parameters, true);
            break;
        case UNMARK:
            markItem(items, parameters, false);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            addItem(items, parameters, command);
            break;
        case DELETE:
            deleteItem(items, parameters);
            break;
        case FIND:
            findItem(items, parameters);
            break;
        case EXIT:
            return -1;
        default:
            throw new MomException(Message.ERROR_INVALID_COMMAND.toString());
        }
        
        return 0;
    }

    /**
     * Check if a number is in the bounds of the items list.
     * For mark, unmark, delete action.
     * 
     * @param parameters Number that will be validated.
     * @param itemsSize Total size of the items list.
     * @return number that is within the bounds of items list in Integer format.
     * @throws MomException When specified item is out of bounds or invalid.
     */
    private static int validateItem(String parameters, int itemsSize) throws MomException {
        try {
            int num = Integer.parseInt(parameters) - 1;
            if (num >= 0 && num < itemsSize) {
                return num;
            } else {
                throw new MomException(Message.ERROR_MARK_OUT_OF_BOUNDS.toString());
            }
        } catch (NumberFormatException err) {
            throw new MomException(Message.ERROR_MARK_INVALID_PARAMETER.toString());
        }
    }

    private static void printList(ArrayList<Item> items) {
        MessageAction.printList(items);
    }

    private static void markItem(ArrayList<Item> items, String parameters, Boolean mark) throws MomException {
        int num = validateItem(parameters, items.size());
        Item newItem = ItemAction.markItem(items.get(num), mark);
        items.set(num, newItem);
    }

    private static void addItem(ArrayList<Item> items, String parameters, Command command) throws Exception {
        if (command.equals(Command.TODO)) {
            items.add(TodoAction.addTodo(parameters, items.size()));
        } else if (command.equals(Command.DEADLINE)) {
            items.add(DeadlineAction.addDeadline(parameters, items.size()));
        } else if (command.equals(Command.EVENT)) {
            items.add(EventAction.addEvent(parameters, items.size()));
        }
    }

    private static void deleteItem(ArrayList<Item> items, String parameters) throws MomException {
        int num = validateItem(parameters, items.size());
        ItemAction.deleteItem(items.remove(num), items.size());
    }

    private static void findItem(ArrayList<Item> items, String parameters) throws MomException {
        if (parameters.isEmpty()) {
            throw new MomException(Message.ERROR_FIND_MISSING_PARAMETER.toString());
        }

        ArrayList<Item> filteredItems = new ArrayList<Item>();
        for (Item item : items) {
            if (item.getDescription().contains(parameters)) {
                filteredItems.add(item);
            }
        }

        MessageAction.printFilteredList(filteredItems);
    }
}