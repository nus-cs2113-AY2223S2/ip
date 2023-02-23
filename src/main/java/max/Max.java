package max;

import max.command.Command;
import max.command.CommandParser;
import max.command.CommandValidator;
import max.command.InvalidCommandException;
import max.task.*;

import java.util.HashMap;
import java.util.Scanner;

public class Max {
    private static boolean isListening;
    private static boolean isDebugMode = false;

    private static TaskManager taskManager;

    public static void setIsListening(boolean isListening) {
        Max.isListening = isListening;
    }

    public static void printBorder() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    public static void exit() {
        setIsListening(false);
        System.out.print("Goodbye! Thank you for using MAX.\n");
    }


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
            System.out.println(exception.getMessage());
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
            String taskNumString = commandPayload.get("mark");
            taskManager.markTask(taskNumString, true);
            break;
        case UNMARK:
            taskNumString = commandPayload.get("unmark");
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
            taskNumString = commandPayload.get("delete");
            taskManager.deleteTask(taskNumString);
            break;
        case DEBUG:
            isDebugMode = true;
            taskManager.resetTaskList();
            System.out.println("MAX is now in debug mode. No data will be saved or loaded from disk.");
            System.out.println("To exit debug mode, restart MAX.");
            break;
        default:
            // { Command.UNKNOWN_COMMAND }
            System.out.println("Awoo? I don't understand that command.");
            break;
        }

        // Backup data after every command
        if (!isDebugMode) {
            taskManager.saveData();
        }
    }

    public static void greet() {
        printBorder();
        System.out.println("Hello! I'm Max, your PAWsonal productivity assistant");
        System.out.println("What can I do for you to MAXimize your day?");
        printBorder();
    }

    public static void main(String[] args) {
        String logo = " /$$      /$$  /$$$$$$  /$$   /$$\n" +
                "| $$$    /$$$ /$$__  $$| $$  / $$\n" +
                "| $$$$  /$$$$| $$  \\ $$|  $$/ $$/       /^ ^\\\n" +
                "| $$ $$/$$ $$| $$$$$$$$ \\  $$$$/       / 0 0 \\\n" +
                "| $$  $$$| $$| $$__  $$  >$$  $$       V\\ Y /V\n" +
                "| $$\\  $ | $$| $$  | $$ /$$/\\  $$       / - \\\n" +
                "| $$ \\/  | $$| $$  | $$| $$  \\ $$      /    |\n" +
                "|__/     |__/|__/  |__/|__/  |__/     V__)  ||";
        System.out.println(logo);

        // Init task subsystem (Controller)
        taskManager = new TaskManager();
        taskManager.loadData();

        // Greet when data has been loaded and problematic saved data has been highlighted
        greet();
        setIsListening(true);
        // Init IO
        Scanner input = new Scanner(System.in);

        // Event driver loop to continuously listen for inputs
        while (isListening) {
            System.out.print("~$ ");
            String command = input.nextLine();
            printBorder();
            handleCommand(command);
            printBorder();
        }
    }
}
