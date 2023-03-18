import java.util.Scanner;

import mom.command.Command;
import mom.command.CommandAction;
import mom.exceptions.FileException;
import mom.item.Item;
import mom.utils.FileAction;
import mom.utils.Message;
import mom.utils.MessageAction;

import java.util.ArrayList;

public class Mom {
    private static ArrayList<Item> items; // List of items

    public static void main(String[] args) {
        startMom();

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
                    exitMom();
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
    private static void startMom() {
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
    private static void exitMom() {
        System.out.println(Message.INFO_EXIT);
        System.exit(0);
    }
}