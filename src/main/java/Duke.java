import command.Command;
import command.DeadlineCommand;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.Files.createDirectories;

public class Duke {
    private static final String line = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static boolean isDone = false;
    private static final String dirPath = "." + File.separator + "data";
    private static final String filePath = dirPath + File.separator + "duke.txt";

    public static void main(String[] args) {
        startDuke();
        runDuke();
        exitDuke();
    }

    private static void greetUser() {
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
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
                System.out.println(line + '\n' + "No existing data found.\nCreated new file \"./data/duke.txt\"" + '\n' + line);
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] parameters = data.split(" \\| ");
                Task task = createTask(parameters);
                tasks[taskCount] = task;
                taskCount += 1;
            }
            scanner.close();
        } catch (DukeException | IOException e) {
            String errorMessage = e.getMessage();
            System.out.println(line + '\n' + errorMessage + '\n' + line);
        }

        System.out.println(line + '\n' + "\"./data/duke.txt\" found.\nData loaded into Duke!" + '\n' + line);
    }

    private static void updateData() throws IOException {
        //format content to write
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            content.append(tasks[i].getDataSummary());
            content.append("\n");
        }
        FileWriter fileWriter = new FileWriter(filePath, false);
        fileWriter.write(content.toString());
        fileWriter.close();
    }

    private static void startDuke() {
        greetUser();
        loadData();
    }

    private static void exitDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        while (!isDone) {
            try {
                String input = in.nextLine();
                String[] commands = Parser.parse(input);
                Command commandObject;
                switch (commands[0]) {
                case "bye":
                    isDone = true;
                    break;
                case "list":
                    commandObject = new ListCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    break;
                case "mark":
                    commandObject = new MarkCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    updateData();
                    break;
                case "unmark":
                    commandObject = new UnmarkCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    updateData();
                    break;
                case "todo":
                    commandObject = new TodoCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    taskCount += 1;
                    updateData();
                    break;
                case "deadline":
                    commandObject = new DeadlineCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    taskCount += 1;
                    updateData();
                    break;
                case "event":
                    commandObject = new EventCommand(commands);
                    commandObject.doCommand(taskCount, tasks);
                    taskCount += 1;
                    updateData();
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | IOException e) {
                String errorMessage = e.getMessage();
                System.out.println(line + '\n' + errorMessage + '\n' + line);
            }
        }
    }
}
