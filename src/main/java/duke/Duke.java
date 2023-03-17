package duke;

/**
 * Duke is a task recording robot with different functions.
 * Functions include: delete, mark, unmark, deadline, todo, event, find
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static Parser parser;
    static UI ui;
    static Storage storage;
    static TaskList taskList;

    public static void main(String[] args) throws IOException {
        storage = new Storage();
        ui = new UI();
        parser = new Parser();
        taskList = new TaskList();
        String command = "";
        Scanner in = new Scanner(System.in);
        loadFile();
        ui.showGreetings();
        while (!command.equals("bye")) {
            ui.printLinebreak();
            command = in.nextLine();
            String[] commandLine = parser.parseCommand(command);
            ui.printLinebreak();
            doCommand(commandLine);
        }
    }

    /**
     * Loads file and parses file contents.
     * @throws IOException
     */
    private static void loadFile() throws IOException {
        File file = storage.createFile();
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            Parser.parseFile(fileReader);
            taskList.incrementTaskNum();
        }
    }

    /**
     * Handles command input by user.
     * @param commandLine the commands the user has inputted
     */
    private static void doCommand(String[] commandLine) {
        switch (commandLine[0]) {
        case "todo":
            CommandHandler.addTodoTask(commandLine);
            break;
        case "deadline":
            CommandHandler.addDeadlineTask(commandLine);
            break;
        case "event":
            CommandHandler.addEventTask(commandLine);
            break;
        case "mark":
            CommandHandler.markTask(commandLine);
            break;
        case "unmark":
            CommandHandler.unmarkTask(commandLine);
            break;
        case "list":
            CommandHandler.listTasks();
            break;
        case "delete":
            CommandHandler.deleteTask(commandLine);
            break;
        case "find":
            CommandHandler.findItem(commandLine);
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        default:
            try {
                throw new IllegalDukeArgumentException();
            } catch (IllegalDukeArgumentException e) {
                System.out.println("Oh no!");;
            }
        }
    }
}
