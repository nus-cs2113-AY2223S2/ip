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
import task.Task;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static boolean isDone = false;

    public static void main(String[] args) {
        startDuke();
        runDuke();
        exitDuke();
    }

    private static void startDuke() {
        ui = new Ui(System.in);
        storage = new Storage();
        ui.greetUser();
        storage.loadData(ui, tasks);
    }

    private static void exitDuke() {
        ui.byeUser();
        System.exit(0);
    }

    private static void runDuke() {
        while (!isDone) {
            try {
                String input = ui.getNextLineInput();
                ArrayList<String> commands = Parser.parse(input);
                Command commandObject;
                String result;
                switch (commands.get(0)) {
                case "bye":
                    isDone = true;
                    result = null;
                    break;
                case "list":
                    commandObject = new ListCommand(commands);
                    result = commandObject.doCommand(tasks);
                    break;
                case "mark":
                    commandObject = new MarkCommand(commands);
                    result = commandObject.doCommand(tasks);
                    storage.updateData(tasks);
                    break;
                case "unmark":
                    commandObject = new UnmarkCommand(commands);
                    result = commandObject.doCommand(tasks);
                    storage.updateData(tasks);
                    break;
                case "todo":
                    commandObject = new TodoCommand(commands);
                    result = commandObject.doCommand(tasks);
                    storage.updateData(tasks);
                    break;
                case "deadline":
                    commandObject = new DeadlineCommand(commands);
                    result = commandObject.doCommand(tasks);
                    storage.updateData(tasks);
                    break;
                case "event":
                    commandObject = new EventCommand(commands);
                    result = commandObject.doCommand(tasks);
                    storage.updateData(tasks);
                    break;
                case "delete":
                    commandObject = new DeleteCommand(commands);
                    result = commandObject.doCommand(tasks);
                    storage.updateData(tasks);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                ui.printCommandResult(result);
            } catch (DukeException | IOException e) {
                String errorMessage = e.getMessage();
                ui.printErrorMessage(errorMessage);
            }
        }
    }
}
