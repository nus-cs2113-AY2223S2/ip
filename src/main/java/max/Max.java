package max;

import max.Ui.Ui;
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

    private static Ui ui;
    private static TaskManager taskManager;

    public static void setIsListening(boolean isListening) {
        Max.isListening = isListening;
    }

    public static void exit() {
        setIsListening(false);
        ui.printMessage("Goodbye! Thank you for using MAX.");
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
            ui.notifyImportant();
            ui.printMessage("MAX is now in debug mode. No data will be saved or loaded from disk.");
            ui.printMessage("To exit debug mode, restart MAX.");
            break;
        case FIND:
        case FETCH:
            // Fetch is used as an alias for find for thematic consistency with MAX
            taskManager.findTasks(commandPayload);
            break;
        default:
            // { CommandType.UNKNOWN_COMMAND }
            ui.printMessage("Awoo? I don't understand that command.");
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
