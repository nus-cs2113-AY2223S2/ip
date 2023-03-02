import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represent a Personal Assistant Chatbot named Duke that helps a person to keep track of various things.
 */
public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static boolean isDone = false;
    private static final int COMMAND_INDEX = 0;
    private static final int EXIT_SUCCESS = 0;


    /**
     * Creates a Duke object and calls its run method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        startDuke();
        runDuke();
        exitDuke();
    }

    private static void startDuke() {
        ui = new Ui(System.in);
        ui.greetUser();
        taskList = new TaskList();
        storage = new Storage();
        storage.loadData(ui, taskList);
    }

    private static void exitDuke() {
        ui.byeUser();
        System.exit(EXIT_SUCCESS);
    }

    private static Command createCommandObject(String command, ArrayList<String> commands) {
        Command commandObject = null;
        switch (command) {
        case "list":
            commandObject = new ListCommand(commands);
            break;
        case "mark":
            commandObject = new MarkCommand(commands);
            break;
        case "unmark":
            commandObject = new UnmarkCommand(commands);
            break;
        case "todo":
            commandObject = new TodoCommand(commands);
            break;
        case "deadline":
            commandObject = new DeadlineCommand(commands);
            break;
        case "event":
            commandObject = new EventCommand(commands);
            break;
        case "delete":
            commandObject = new DeleteCommand(commands);
            break;
        }
        return commandObject;
    }

    private static void runCommand(ArrayList<String> commands) throws DukeException, IOException {
        Command commandObject = createCommandObject(commands.get(COMMAND_INDEX), commands);
        String result = commandObject.doCommand(taskList);
        storage.updateData(taskList);
        ui.printCommandResult(result);
    }

    private static ArrayList<String> getCommands() throws DukeException {
        String input = ui.getNextLineInput();
        return Parser.parse(input);
    }

    private static void handleDukeException(Exception e) {
        String errorMessage = e.getMessage();
        ui.printErrorMessage(errorMessage);
    }

    /**
     * Repeatedly read user input and execute the command.
     * Method stops upon user input "bye".
     */
    private static void runDuke() {
        while (!isDone) {
            try {
                ArrayList<String> commands = getCommands();
                switch (commands.get(COMMAND_INDEX)) {
                case "bye":
                    isDone = true;
                    continue;
                case "list":
                    // Fallthrough
                case "mark":
                    // Fallthrough
                case "unmark":
                    // Fallthrough
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    // Fallthrough
                case "delete":
                    runCommand(commands);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | IOException e) {
                handleDukeException(e);
            }
        }
    }
}
