package duke;

import duke.exceptions.FormatException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.NoDescriptionException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    static String FILEPATH = "duke.txt";
    static ArrayList <Task> tasks = new ArrayList<>();

    public static void addTask(Task t) {
        tasks.add(t);
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }


    public static void info() {
        printLine();
        System.out.println("This command is not valid, please read through the info and try again :)");
        System.out.println("Type: [todo] [something], and the system will add a new todo item to your list");
        System.out.println("Type: [event] [something] from: [when] to: [when], and the system will add an event and the timing");
        System.out.println("Type: [deadline] [something] by: [when], and the system will add a deadline");
        System.out.println("Type: [mark] [number], and the system will mark the item of the number as done");
        System.out.println("Type: [unmark] [number], and the system will unmark the item of the number.");
        System.out.println("Type: bye, to say goodbye to Duke!");
        System.out.println("Hope it helps!! woof a nice day ੯•໒꒱❤︎");
        printLine();
    }

    public static void process(String s) throws InvalidCommandException {
        final String[] commandTypeAndParams = new Parser().parseCommand(s);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        Command command = new Command();
        switch (commandType) {
        case "list":
            command.printListOfTasks(tasks);
            break;
        case "todo":
            try {
                command.addTodo(tasks, commandArgs);
                Ui.showAddTaskMessage(tasks);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a todo cannot be empty.");
                System.out.println("Please try to add todo again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "deadline":
            try {
                command.addDeadline(tasks, commandArgs);
                Ui.showAddTaskMessage(tasks);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a deadline cannot be empty.");
                System.out.println("Please try to add deadline again υ´• ﻌ •`υ");
                printLine();
            } catch (FormatException e) {
                System.out.println("WOOFS!!! The format of entering deadline is incorrect.");
                System.out.println("Please try to add deadline again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "event":
            try {
                command.addEvent(tasks, commandArgs);
                Ui.showAddTaskMessage(tasks);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a event cannot be empty.");
                System.out.println("Please try to add event again υ´• ﻌ •`υ");
                printLine();
            } catch (FormatException e) {
                System.out.println("WOOFS!!! The format of entering event is incorrect.");
                System.out.println("Please try to add event again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "mark":
            try {
                command.markTask(tasks, commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The index of entering task must be stated.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("WOOFS!!! The index of entering task is not valid.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "unmark":
            try {
                command.unmarkTask(tasks, commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The index of entering task must be stated.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("WOOFS!!! The index of entering task is not valid.");
                System.out.println("Please try to mark task again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "delete":
            try {
                TaskList.deleteTask(tasks, commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The index of entering task must be stated.");
                System.out.println("Please try to delete task again υ´• ﻌ •`υ");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("WOOFS!!! The index of entering task is not valid.");
                System.out.println("Please try to delete task again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "find":
            command.find(tasks, commandArgs);
            break;
        case "help":
            info();
            break;
        default:
            throw new InvalidCommandException();
        }

    }

    private static String inputCommand() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        return s;
    }



    private void start() {
        this.ui = new Ui();
        this.storage = new Storage();
    }
    public void run() {
        start();
        ui.showWelcomeMessage();
        storage.initializeStorage(tasks, FILEPATH);
        String s = ui.getUserCommand();
        while (!s.equals("bye")) {
            try {
                process(s);
            } catch (InvalidCommandException e) {
                System.out.println("WOOF!! The command is not found, please type 'help' for more info");
                printLine();
            }
            s = inputCommand();
        }
        ui.showGoodByeMessage();
        storage.storeChanges(FILEPATH, tasks);
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
