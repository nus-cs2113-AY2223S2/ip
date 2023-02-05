import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static boolean isListening;
    private static Task[] tasks = new Task[100];
    private static int numTasks;


    public static void setIsListening(boolean keepListening) {
        Duke.isListening = keepListening;
    }

    public static void printBorder() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    public static void exit() {
        System.out.print("Goodbye! Thank you for using MAX.\n");
    }

    public static Task createTask(HashMap<String, String> commandMap, Command command) {
        // Start from 1 as cop
        Task newTask = null;
        if (command.equals(Command.TASK_TODO)) {
            // To-do task
            String description = commandMap.get("todo");
            newTask = new Todo(description);

        } else if (command.equals(Command.TASK_DEADLINE)) {
            // Guard clauses to check for validity
            if (commandMap.size() < 2) {
                System.out.println("Not enough arguments!");
                return newTask;
            }
            if (!commandMap.containsKey("by")) {
                System.out.println("Missing --by argument!");
                return newTask;
            }
            // Deadline task
            String description = commandMap.get("deadline");
            String deadline = commandMap.get("by");
            newTask = new Deadline(description, deadline);
        } else if (command.equals(Command.TASK_EVENT)) {

            if (commandMap.size() < 3) {
                System.out.println("Not enough arguments!");
                return newTask;
            }
            if (!commandMap.containsKey("from")) {
                System.out.println("Missing --from argument!");
                return newTask;
            }
            if (!commandMap.containsKey("to")) {
                System.out.println("Missing --to argument!");
                return newTask;
            }
            // Event task
            String description = commandMap.get("event");
            String from = commandMap.get("from");
            String to = commandMap.get("to");
            newTask = new Event(description, from, to);
        }
        return newTask;
    }

    public static void handleCommand(String command) {
        CommandParser commandParser = new CommandParser();

        // Update the keepAlive flag
        String[] commandList = commandParser.splitIntoSubcommands(command);

        // Process subcommands into <subcommand, payload>
        HashMap<String,String> subcommandMap = commandParser.getSubcommandMap(commandList);

        Command mainCommand = commandParser.getCommandType(commandList[0]);
        switch (mainCommand) {
        case EXIT:
            setIsListening(false);
            exit();
            break;
        case LIST:
            System.out.println("Here's what's in your list:");
            for (int i = 0; i < numTasks; ++i) {
                // Print number, box, description in that order
                Task curr = tasks[i];
                System.out.print(i + 1 + ". " + curr.getDescription() + '\n');
            }
            break;
        case MARK:
            String taskNumStr = commandParser.extractTextFromSubcommand(commandList[0]);
            if (taskNumStr.length() == 0) {
                System.out.println("Missing number!");
                return;
            }
            // Unsafe parsing below
            int taskNum = Integer.parseInt(taskNumStr) - 1; // Convert to 0-idx
            if (taskNum < 0 || taskNum >= numTasks) {
                System.out.println("Invalid task number!");
                return;
            }
            // Update its value
            tasks[taskNum].markAsDone();
            System.out.println("Okay, marking this task as done: ");
            System.out.println(tasks[taskNum].getDescription());
            break;
        case UNMARK:
            taskNumStr = commandParser.extractTextFromSubcommand(commandList[0]);
            if (taskNumStr.length() == 0) {
                System.out.println("Missing number!");
                return;
            }
            // Unsafe parsing below
            taskNum = Integer.parseInt(taskNumStr) - 1; // Convert to 0-idx
            if (taskNum < 0 || taskNum >= numTasks) {
                System.out.println("Invalid task number!");
                return;
            }
            // Update its value
            tasks[taskNum].markAsUndone();
            System.out.println("Okay, setting this task as undone: ");
            System.out.println(tasks[taskNum].getDescription());
            break;
        case TASK_EVENT:
        case TASK_DEADLINE:
        case TASK_TODO:
            Task newTask = createTask(subcommandMap, mainCommand);
            if (newTask == null) {
                System.out.println("Task could not be created.");
                return;
            }
            tasks[numTasks] = newTask;
            numTasks++;
            System.out.println("Got it. Task added:");
            System.out.println(newTask.getDescription());
            System.out.println("You now have " + numTasks + " tasks in your list.");
            break;
        default:
            // { Command.UNKNOWN_COMMAND }
            System.out.println("I don't quite understand...");
            break;
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

        greet();
        setIsListening(true);

        // Init tasks subsystem
        numTasks = 0;

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
