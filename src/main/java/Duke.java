import java.util.Scanner;
import java.util.ArrayList;

import duke.command.Command;
import duke.command.CommandAction;

import duke.item.Item;

import duke.utils.Message;
import duke.utils.MessageAction;
import duke.utils.FileAction;

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

                int status = CommandAction.executeCommand(command, parameters, items);
                if (status == -1) {
                    in.close();
                    exitDuke();
                }

                FileAction.exportItems(items);
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
     * Exits the application.
     */
    private static void exitDuke() {
        System.out.println(Message.INFO_EXIT);
        System.exit(0);
    }
}