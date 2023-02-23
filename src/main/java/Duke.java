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
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.Files.createDirectories;

public class Duke {
    private static Ui ui;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static boolean isDone = false;
    private static final String dirPath = "." + File.separator + "data";
    private static final String filePath = dirPath + File.separator + "duke.txt";

    public static void main(String[] args) {
        startDuke();
        runDuke();
        exitDuke();
    }


    private static Task createTask(String[] parameters) throws DukeException {
        Task newTask;
        switch (parameters[0]) {
        case "T":
            newTask = new Todo(parameters[2]);
            break;
        case "D":
            newTask = new Deadline(parameters[2], parameters[3]);
            break;
        case "E":
            newTask = new Event(parameters[2], parameters[3], parameters[4]);
            break;
        default:
            throw new DukeException("Unrecognized data!");
        }

        if (parameters[1].equals("1")) {
            newTask.markDone();
        }

        return newTask;
    }

    private static void loadData() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                Path dirPath = Paths.get("./data");
                createDirectories(dirPath);
                file.createNewFile();
                ui.printFileCreated();
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] parameters = data.split(" \\| ");
                Task task = createTask(parameters);
                tasks.add(task);
            }
            scanner.close();
        } catch (DukeException | IOException e) {
            String errorMessage = e.getMessage();
            ui.printErrorMessage(errorMessage);
        }
        ui.printDataLoadSuccess();
    }

    private static void updateData() throws IOException {
        //format content to write
        StringBuilder content = new StringBuilder();
        for (Task task : tasks) {
            content.append(task.getDataSummary());
            content.append("\n");
        }
        FileWriter fileWriter = new FileWriter(filePath, false);
        fileWriter.write(content.toString());
        fileWriter.close();
    }

    private static void startDuke() {
        ui = new Ui(System.in);
        ui.greetUser();
        loadData();
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
                    updateData();
                    break;
                case "unmark":
                    commandObject = new UnmarkCommand(commands);
                    result = commandObject.doCommand(tasks);
                    updateData();
                    break;
                case "todo":
                    commandObject = new TodoCommand(commands);
                    result = commandObject.doCommand(tasks);
                    updateData();
                    break;
                case "deadline":
                    commandObject = new DeadlineCommand(commands);
                    result = commandObject.doCommand(tasks);
                    updateData();
                    break;
                case "event":
                    commandObject = new EventCommand(commands);
                    result = commandObject.doCommand(tasks);
                    updateData();
                    break;
                case "delete":
                    commandObject = new DeleteCommand(commands);
                    result = commandObject.doCommand(tasks);
                    updateData();
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
