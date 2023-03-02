package max;

import max.ui.Ui;
import max.command.Command;
import max.command.CommandParser;
import max.command.CommandValidator;
import max.command.InvalidCommandException;
import max.task.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * MAX is a CLI personal productivity assistant, built as part of CS2113 iP
 * <p>
 * MAX allows users to track and manage their tasks, and offers persistent data storage.
 * MAX can track tasks that are: Deadlines, events and todos that encompass most users' needs
 * MAX also comes with a special DEBUG mode for developers who want to extend on MAX for testing
 */
public class Max {
    private static boolean isListening;
    private static boolean isDebugMode = false;

    private static Ui ui;
    private static TaskManager taskManager;

    private static final String MESSAGE_DEBUG_MODE = "MAX is now in debug mode. No data will be saved or loaded from disk.";
    private static final String MESSAGE_DEBUG_HELP = "To exit debug mode, restart MAX.";
    private static final String MESSAGE_GOODBYE = "Goodbye! Thank you for using MAX.";
    private static final String MESSAGE_UNKNOWN_COMMAND = "Awoo? I don't understand that command.";

    public static void setIsListening(boolean isListening) {
        Max.isListening = isListening;
    }

    /**
     * Kill Max's event driver loop and print the goodbye message
     */
    public static void exit() {
        setIsListening(false);
        ui.printMessage(MESSAGE_GOODBYE);
    }


    /**
     * Takes in user input as a string, figures out what it is and executes the correct command action.
     *
     * @param command Unprocessed user input from console
     */
    public static void handleCommand(String command) {
        CommandParser commandParser = new CommandParser();
        CommandValidator commandValidator = new CommandValidator();

        // Update the keepAlive flag
        String[] commandList = commandParser.splitIntoCommands(command);

        // Process subcommands into <subcommand, payload>
        HashMap<String, String> commandPayload = commandParser.getCommandPayloadMap(commandList);

        Command mainCommand = commandParser.getCommandType(commandList[0]);

        // Validate command to ensure it has:
        // 1. Correct argument size
        // 2. Correct argument names
        // WARNING: This validation does not check for payload correctness
        try {
            commandValidator.validateCommandPayloadMap(mainCommand, commandPayload);
        } catch (InvalidCommandException exception) {
            ui.printMessage(exception.getMessage());
            return;
        }

        // Assertion: commandPayload is correct for its given mainCommand
        switch (mainCommand) {
        case EXIT:
            exit();
            break;
        case LIST:
            taskManager.printTasklist();
            break;
        case MARK:
            String taskNumString = commandPayload.get(mainCommand.getMainCommand());
            taskManager.markTask(taskNumString, true);
            break;
        case UNMARK:
            taskNumString = commandPayload.get(mainCommand.getMainCommand());
            taskManager.markTask(taskNumString, false);
            break;
        case TASK_EVENT:
        case TASK_DEADLINE:
        case TASK_TODO:
            try {
                taskManager.createTask(commandPayload, mainCommand);
            } catch (TaskException exception) {
                System.out.println(exception.getMessage());
            }
            break;
        case DELETE:
            taskNumString = commandPayload.get(mainCommand.getMainCommand());
            taskManager.deleteTask(taskNumString);
            break;
        case DEBUG:
            isDebugMode = true;
            taskManager.resetTaskList();
            ui.notifyImportant();
            ui.printMessage(MESSAGE_DEBUG_MODE);
            ui.printMessage(MESSAGE_DEBUG_HELP);
            break;
        case FIND:
        case FETCH:
            // Fetch is used as an alias for find for thematic consistency with MAX
            taskManager.findTasks(commandPayload);
            break;
        default:
            // { CommandType.UNKNOWN_COMMAND }
            ui.printMessage(MESSAGE_UNKNOWN_COMMAND);
            break;
        }

        // Backup data after every command
        if (!isDebugMode) {
            taskManager.saveData();
        }
    }

    public static void main(String[] args) {
        ui = new Ui();
        ui.greet();

        // Init task subsystem (Controller)
        taskManager = new TaskManager();
        taskManager.loadData();

        // Greet when data has been loaded and problematic saved data has been highlighted
        ui.greet();
        setIsListening(true);

        // Init IO
        Scanner input = new Scanner(System.in);

        // Event driver loop to continuously listen for inputs
        while (isListening) {
            ui.printCommandPrompt();
            String command = input.nextLine();
            ui.printBorder();
            handleCommand(command);
            ui.printBorder();
        }
    }
}
