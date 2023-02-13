package max;

import max.command.Command;
import max.command.CommandParser;
import max.command.CommandValidator;
import max.command.InvalidCommandException;
import max.data.PersistentDataHandler;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.Todo;

import java.util.HashMap;
import java.util.Scanner;

public class Max {
    private static boolean isListening;
    private static boolean isDebugMode = false;
    private static final int TASK_NUMBER_LIMIT = 100;
    private static Task[] tasks = new Task[TASK_NUMBER_LIMIT];
    private static int numTasks;


    public static void setIsListening(boolean keepListening) {
        Max.isListening = keepListening;
    }

    public static void printBorder() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    public static void exit() {
        setIsListening(false);
        System.out.print("Goodbye! Thank you for using MAX.\n");
    }

    public static Task createTask(HashMap<String, String> commandMap, Command command) throws InvalidCommandException {
        // Assertion: commandMap has the correct subcommands & length
        Task newTask = null;
        if (command.equals(Command.TASK_TODO)) {
            // To-do task
            String description = commandMap.get("todo");
            newTask = new Todo(description);
        } else if (command.equals(Command.TASK_DEADLINE)) {
            // Deadline task
            String description = commandMap.get("deadline");
            String deadline = commandMap.get("by");
            newTask = new Deadline(description, deadline);
        } else if (command.equals(Command.TASK_EVENT)) {
            // Event task
            String description = commandMap.get("event");
            String from = commandMap.get("from");
            String to = commandMap.get("to");
            newTask = new Event(description, from, to);
        }
        if (newTask == null) {
            // Safety check in case the assertion fails
            throw new InvalidCommandException("Throw me a bone here, I couldn't create a task!");
        }
        return newTask;
    }

    public static void addTaskToList(Task newTask) throws IndexOutOfBoundsException {
        if (numTasks == TASK_NUMBER_LIMIT) {
            throw new IndexOutOfBoundsException("MAX is a dog, and has limited memory. Too many tasks for MAX to remember!");
        }
        tasks[numTasks] = newTask;
        numTasks++;
    }

    public static void printTasklist() {
        if (numTasks <= 0) {
            System.out.println("There's nothing in your list. I'm gonna bite you.");
            return;
        }
        System.out.println("Here's what's in your list:");
        for (int i = 0; i < numTasks; ++i) {
            // Print number, box, description in that order
            Task curr = tasks[i];
            System.out.print(i + 1 + ". " + curr.getDescription() + '\n');
        }
    }

    public static void markTask(String taskNumString, boolean isDone) {
        int taskNum;

        try {
            taskNum = Integer.parseInt(taskNumString) - 1; // Convert to 0-idx
        } catch (NumberFormatException exception) {
            System.out.println("I'm a dog, but even I know that you didn't enter a number.");
            return;
        }

        // Guard clause to check for taskNum validity
        if (taskNum < 0 || taskNum >= numTasks) {
            System.out.println("Invalid task number!");
            return;
        }

        // Update the task's done status
        if (isDone) {
            tasks[taskNum].markAsDone();
            System.out.println("Okay, marking this task as done: ");
        } else {
            tasks[taskNum].markAsUndone();
            System.out.println("Okay, setting this task as undone: ");
        }
        System.out.println(tasks[taskNum].getDescription());
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
            printTasklist();
            break;
        case MARK:
            String taskNumString = commandPayload.get("mark");
            markTask(taskNumString, true);
            break;
        case UNMARK:
            taskNumString = commandPayload.get("unmark");
            markTask(taskNumString, false);
            break;
        case TASK_EVENT:
        case TASK_DEADLINE:
        case TASK_TODO:
            // TODO: Consider refactoring Task logic to a TaskHandler class
            try {
                Task newTask = createTask(commandPayload, mainCommand);
                addTaskToList(newTask);
                System.out.println("Got it. Task added:");
                System.out.println(newTask.getDescription());
                System.out.println("You now have " + numTasks + " tasks in your list.");

            } catch (InvalidCommandException | IndexOutOfBoundsException exception) {
                System.out.println(exception.getMessage());
            }
            break;
        case DEBUG:
            tasks = new Task[TASK_NUMBER_LIMIT];
            System.out.println("MAX is now in debug mode. No data will be saved or loaded from disk.");
            System.out.println("To exit debug mode, restart MAX.");
            break;
        default:
            // { Command.UNKNOWN_COMMAND }
            System.out.println("Awoo? I don't understand that command.");
            break;
        }
        // Backup data after every command
        if(!isDebugMode){
            PersistentDataHandler dataHandler = new PersistentDataHandler();
            dataHandler.saveTasksToDisk(tasks);
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

        // Init tasks subsystem
        PersistentDataHandler dataHandler = new PersistentDataHandler();
        tasks = dataHandler.loadTasksFromDisk();
        numTasks = tasks.length;

        // Init IO
        Scanner input = new Scanner(System.in);
        greet();
        setIsListening(true);


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
