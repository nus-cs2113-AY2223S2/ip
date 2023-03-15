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
    private static final CommandParser commandParser = new CommandParser();
    private static final CommandValidator commandValidator = new CommandValidator();
    private static final String MESSAGE_DEBUG_MODE = "MAX is now in debug mode. No data will be saved/loaded.";
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

    private static void startDebugMode() {
        isDebugMode = true;
        taskManager.resetTaskList();
        ui.notifyImportant();
        ui.printMessage(MESSAGE_DEBUG_MODE);
        ui.printMessage(MESSAGE_DEBUG_HELP);
    }

    private static void executeList() {
        taskManager.printTasklist();
    }

    /**
     * Marks a task as done or not done using TaskManager
     *
     * @param commandPayload argument-payload map of user input
     * @param mainCommand    command derived from user input
     */
    private static void executeMarkTask(HashMap<String, String> commandPayload, Command mainCommand) {
        String mainCommandString = mainCommand.getMainCommand();
        String taskNumString = commandPayload.get(mainCommandString);
        if (mainCommand.equals(Command.MARK)) {
            taskManager.markTask(taskNumString, true);
        } else if (mainCommand.equals(Command.UNMARK)) {
            taskManager.markTask(taskNumString, false);
        }
    }

    /**
     * Adds a task using TaskManager
     *
     * @param commandPayload argument-payload map of user input
     * @param mainCommand    command derived from user input
     */
    private static void executeAddTask(HashMap<String, String> commandPayload, Command mainCommand) {
        try {
            taskManager.createTask(commandPayload, mainCommand);
        } catch (TaskException exception) {
            ui.printMessage(exception.getMessage());
        }
    }

    /**
     * Removes a task using TaskManager
     *
     * @param commandPayload argument-payload map of user input
     * @param mainCommand    command derived from user input
     */
    private static void executeDeleteTask(HashMap<String, String> commandPayload, Command mainCommand) {
        String taskNumString = commandPayload.get(mainCommand.getMainCommand());
        taskManager.deleteTask(taskNumString);
    }


    /**
     * Prints error message for unknown commands
     */
    private static void handleUnknownCommand() {
        ui.printMessage(MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Finds tasks based on a query using TaskManager
     *
     * @param commandPayload argument-payload map of user input
     * @param mainCommand    command derived from user input
     */
    private static void executeFindTasks(HashMap<String, String> commandPayload, Command mainCommand) {
        String argumentKey = mainCommand.getMainCommand();
        taskManager.findTasks(commandPayload, argumentKey);
    }

    /**
     * Matches the mainCommand to the proper task method to be executed
     *
     * @param commandPayload argument-payload map of user input
     * @param mainCommand    command derived from user input
     */
    private static void executeCommand(HashMap<String, String> commandPayload, Command mainCommand) {
        switch (mainCommand) {
        case EXIT:
            exit();
            break;
        case LIST:
            executeList();
            break;
        case MARK:
        case UNMARK:
            executeMarkTask(commandPayload, mainCommand);
            break;
        case TASK_EVENT:
        case TASK_DEADLINE:
        case TASK_TODO:
            executeAddTask(commandPayload, mainCommand);
            break;
        case DELETE:
            executeDeleteTask(commandPayload, mainCommand);
            break;
        case DEBUG:
            startDebugMode();
            break;
        case FIND:
        case FETCH:
            executeFindTasks(commandPayload, mainCommand);
            break;
        default:
            handleUnknownCommand();
            break;
        }
    }

    /**
     * Takes in user input as a string, figures out what it is and executes the correct command action.
     *
     * @param command Unprocessed user input from console
     */
    public static void handleCommand(String command) {
        String[] commandList = commandParser.splitIntoCommands(command);

        // Process subcommands into <subcommand, payload>
        HashMap<String, String> commandPayload = commandParser.getCommandPayloadMap(commandList);
        Command mainCommand = commandParser.getCommandType(commandList[0]);

        // Ensure command has correct argument size and correct argument names
        try {
            commandValidator.validateCommandPayloadMap(mainCommand, commandPayload);
        } catch (InvalidCommandException exception) {
            ui.printMessage(exception.getMessage());
            return;
        }

        // Assertion: commandPayload is correct for its given mainCommand
        executeCommand(commandPayload, mainCommand);

        // Backup data after every command
        if (!isDebugMode) {
            taskManager.saveData();
        }
    }

    public static void main(String[] args) {
        // Init task subsystem (Controller)
        taskManager = new TaskManager();
        taskManager.loadData();

        // Greet when data has been loaded and problematic saved data has been highlighted
        ui = new Ui();
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
