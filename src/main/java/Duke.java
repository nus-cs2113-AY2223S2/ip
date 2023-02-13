import java.util.Scanner;
import java.util.ArrayList;

import duke.item.Item;
import duke.item.ItemAction;
import duke.remind.RemindAction;
import duke.deadline.DeadlineAction;
import duke.event.EventAction;

import duke.utils.Command;
import duke.utils.CommandAction;
import duke.utils.Message;
import duke.utils.MessageAction;
import duke.utils.FileAction;

import duke.exceptions.DukeException;
import duke.exceptions.FileException;

public class Duke {
    private static ArrayList<Item> items; // List of items

    public static void main(String[] args) {
        startDuke();

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine();
            System.out.println(Message.LINE);

            try {
                Command command = CommandAction.getCommand(input);
                String parameters = CommandAction.getParameters(input);

                switch(command) {
                case LIST:
                    MessageAction.printList(items);
                    break;
                case MARK: {
                    int num = CommandAction.validateItem(parameters, items.size());
                    Item newItem = ItemAction.markItem(items.get(num), true);
                    items.set(num, newItem);
                    FileAction.exportItems(items);
                    break;
                }
                case UNMARK: {
                    int num = CommandAction.validateItem(parameters, items.size());
                    Item newItem = ItemAction.markItem(items.get(num), false);
                    items.set(num, newItem);
                    FileAction.exportItems(items);
                    break;
                }
                case REMIND:
                    items.add(RemindAction.addRemind(parameters, items.size()));
                    FileAction.exportItems(items);
                    break;
                case DEADLINE:
                    items.add(DeadlineAction.addDeadline(parameters, items.size()));
                    FileAction.exportItems(items);
                    break;
                case EVENT:
                    items.add(EventAction.addEvent(parameters, items.size()));
                    FileAction.exportItems(items);
                    break;
                case DELETE: {
                    int num = CommandAction.validateItem(parameters, items.size());
                    ItemAction.deleteItem(items.remove(num), items.size());
                    FileAction.exportItems(items);
                    break;
                }
                case EXIT: {
                    in.close();
                    exitDuke();
                    break;
                }
                default:
                    throw new DukeException(Message.ERROR_INVALID_COMMAND.toString());
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } finally {
                System.out.println(Message.LINE);
            }
        }
    }

    /**
     * Checks for data file and starts the application.
     */
    private static void startDuke() {
        try {
            items = FileAction.importItems();
        } catch (FileException err) {
            System.out.println(err.getMessage());
            System.exit(1);
        } finally {
            MessageAction.printWelcomeMessage();
        }
    }

    /**
     * Saves the data file and exits the application.
     */
    private static void exitDuke() {
        System.out.println(Message.INFO_EXIT);
        System.exit(0);
    }
}