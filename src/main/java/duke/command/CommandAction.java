package duke.command;

import java.util.ArrayList;

import duke.item.Item;
import duke.item.ItemAction;
import duke.todo.TodoAction;
import duke.deadline.DeadlineAction;
import duke.event.EventAction;

import duke.utils.Message;
import duke.utils.MessageAction;

import duke.exceptions.DukeException;

public class CommandAction {
    /**
     * Retrieve the command from the user's entered input.
     * Invalid command will return an error.
     * 
     * @param input the user inputted text
     * @return command specified by the user
     * @throws DukeException when an invalid command is entered
     */
    public static Command getCommand(String input) throws DukeException {
        try {
            String[] splitInput = input.split(" ", 2);
            Command command = Command.valueOf(splitInput[0].toUpperCase());
            return command;
        } catch (IllegalArgumentException err) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND.toString());
        }
    }

    /**
     * Retrieve the parameters from the user's entered input.
     * If there is no parameters specified, return empty string.
     * 
     * @param input the user inputted text
     * @return parameters specified by the user
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
     * @param input the user inputted text
     * @return parameters specified by the user
     * @throws IllegalArgumentException when check fails
     */
    public static void areValidParameters(String[] parameters) throws IllegalArgumentException {
        for (String parameter : parameters) {
            if (parameter.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    // 0 for ok, -1 for exit
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
        case EXIT:
            return -1;
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND.toString());
        }
        
        return 0;
    }

    public static int validateItem(String parameters, int itemsSize) throws DukeException {
        try {
            int num = Integer.parseInt(parameters) - 1;
            if (num >= 0 && num < itemsSize) {
                return num;
            } else {
                throw new DukeException(Message.ERROR_MARK_OUT_OF_BOUNDS.toString());
            }
        } catch (NumberFormatException err) {
            throw new DukeException(Message.ERROR_MARK_INVALID_PARAMETER.toString());
        }
    }

    private static void printList(ArrayList<Item> items) {
        MessageAction.printList(items);
    }

    private static void markItem(ArrayList<Item> items, String parameters, Boolean mark) throws DukeException {
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

    private static void deleteItem(ArrayList<Item> items, String parameters) throws DukeException {
        int num = validateItem(parameters, items.size());
        ItemAction.deleteItem(items.remove(num), items.size());
    }
}