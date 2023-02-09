package duke.command;

import java.util.Scanner;

import duke.command.CommandHandler;

public class UserCommandManager {

    CommandHandler commandHandler = new CommandHandler();

    /**
     * Handles the command (i.e. type of task) by user
     * and parse the arguments to add to task list
     *
     * @param userCommand type of action user wants to perform
     * @param userInput   arguments to the action (i.e. task description)
     */
    public void handleCommands(String userCommand, String userInput) {
        Scanner scanner = new Scanner(userInput);
        switch (userCommand) {
        case "list":
            commandHandler.handleListCommand();
            break;
        case "mark":
            commandHandler.handleMarkAsDone(userInput.replace(" ", ""));
            break;
        case "unmark":
            commandHandler.handleMarkAsUndone(userInput.replace(" ", ""));
            break;
        case "todo":
            commandHandler.handleTodoCommand(scanner.nextLine());
            break;
        case "deadline":
            commandHandler.handleDeadlineCommand(scanner.nextLine().trim().split("/by"));
            break;
        case "event":
            commandHandler.handleEventCommand(userInput);
            break;
        case "delete":
            commandHandler.handleDeleteCommand(userInput.replace(" ", ""));
            break;
        default:
            commandHandler.handleUnknownCommand();
            break;
        }
    }
}
