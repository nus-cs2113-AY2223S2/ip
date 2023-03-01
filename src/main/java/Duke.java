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

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static boolean isDone = false;

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
                    result = commandObject.doCommand(taskList);
                    break;
                case "mark":
                    commandObject = new MarkCommand(commands);
                    result = commandObject.doCommand(taskList);
                    storage.updateData(taskList);
                    break;
                case "unmark":
                    commandObject = new UnmarkCommand(commands);
                    result = commandObject.doCommand(taskList);
                    storage.updateData(taskList);
                    break;
                case "todo":
                    commandObject = new TodoCommand(commands);
                    result = commandObject.doCommand(taskList);
                    storage.updateData(taskList);
                    break;
                case "deadline":
                    commandObject = new DeadlineCommand(commands);
                    result = commandObject.doCommand(taskList);
                    storage.updateData(taskList);
                    break;
                case "event":
                    commandObject = new EventCommand(commands);
                    result = commandObject.doCommand(taskList);
                    storage.updateData(taskList);
                    break;
                case "delete":
                    commandObject = new DeleteCommand(commands);
                    result = commandObject.doCommand(taskList);
                    storage.updateData(taskList);
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
