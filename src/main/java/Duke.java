import java.util.HashMap;
import java.util.Scanner;

public class Duke {
    private static boolean isListening;
    private static Task[] tasks = new Task[100];
    private static int numTasks;


    public static void setIsListening(boolean keepListening) {
        Duke.isListening = keepListening;
    }

    public static void PrintBorder() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    public static void Exit() {
        System.out.print("Goodbye! Thank you for using MAX.\n");
    }

    public static String[] splitIntoSubcommands(String command) {
        // " -" splits the string into substrings
        // if the substring starts with two dashes '--' and is preceded by whitespace
        // For example: deadline my_task --by -xyz sunday-monday
        // Will output: ["deadline my_task", "by -xyz sunday-monday"]
        // For now, this does not account for accidental or adversarial '--' input at the start of words
        // but this is an unlikely edge case that can be dealt with later
        String[] args = command.split(" --");
        for (String arg : args) {
            System.out.println(arg);
        }
        return args;
    }

    public static HashMap<String, String> getSubcommandMap(String[] commandList) {
        // Each command can be processed into a subcommand and its corresponding text value
        // For example: deadline work on CS2113 --by Sunday
        // Should map to: <"deadline", "work on CS2113">, <by, Sunday>
        HashMap<String, String> commandMap = new HashMap<>();
        for (String cmdStr : commandList) {
            String cmd = extractCommandFromSubcommand(cmdStr);
            String text = extractTextFromSubcommand(cmdStr);
            commandMap.put(cmd, text);
        }
        return commandMap;
    }

    public static String extractCommandFromSubcommand(String subcommand) {
        // Subcommands have format <subcommand> <text_to_extract>
        // Return the <subcommand>
        // For example, the deadline subcommand: "by Friday, 13th"
        // Should return "by"
        String[] words = subcommand.split(" ");
        return words[0];
    }

    public static String extractTextFromSubcommand(String subcommand) {
        // Subcommands have format <subcommand> <text_to_extract>
        // Return the <subcommand> and <text_to_extract> in a string array
        // For example, the deadline subcommand: "by Friday, 13th"
        // Should return ["by" , "Friday, 13th"]
        String[] words = subcommand.split(" ");
        String subcommandText = "";
        for (int i = 1; i < words.length; ++i) {
            subcommandText = subcommandText.concat(words[i]);
            if (i != words.length - 1) {
                subcommandText = subcommandText.concat(" ");
            }
        }
        return subcommandText;
    }

    public static Task createTask(HashMap<String, String> commandMap, String taskType) {
        // Start from 1 as cop
        Task newTask = null;
        if (taskType.equals("todo")) {
            // To-do task
            String description = commandMap.get("todo");
            newTask = new Todo(description);

        } else if (taskType.equals("deadline")) {
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
        } else if (taskType.equals("event")) {

            if (commandMap.size() < 3) {
                System.out.println("Not enough arguments!");
                return newTask;
            }
            if (!commandMap.containsKey("from")) {
                System.out.println("Missing --from argument!");
            }
            if (!commandMap.containsKey("to")) {
                System.out.println("Missing --to argument!");
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
        // Update the keepAlive flag
        String[] commandList = splitIntoSubcommands(command);
        String mainCommand = extractCommandFromSubcommand(commandList[0]);
        switch (mainCommand) {
        case "Bye":
        case "bye":
        case "exit":
            setIsListening(false);
            Exit();
            break;
        case "list":
            System.out.println("Here's what's in your list:");
            for (int i = 0; i < numTasks; ++i) {
                // Print number, box, description in that order
                Task curr = tasks[i];
                System.out.print(i + 1 + ". " + curr.getDescription() + '\n');
            }
            break;
        case "mark":
            String taskNumStr = extractTextFromSubcommand(commandList[0]);
            // Unsafe parsing below
            int taskNum = Integer.parseInt(taskNumStr) - 1; // Convert to 0-idx
            if (taskNum < 0 || taskNum >= commandList.length) {
                System.out.println("Invalid task number!");
                return;
            }
            // Update its value
            tasks[taskNum].markAsDone();
            System.out.println("Okay, marking this task as done: ");
            System.out.println(tasks[taskNum].getDescription());
            break;
        case "unmark":
            taskNumStr = extractTextFromSubcommand(commandList[0]);
            // Unsafe parsing below
            taskNum = Integer.parseInt(taskNumStr) - 1; // Convert to 0-idx
            if (taskNum < 0 || taskNum > commandList.length) {
                System.out.println("Invalid task number!");
                return;
            }
            // Update its value
            tasks[taskNum].markAsUndone();
            System.out.println("Okay, setting this task as undone: ");
            System.out.println(tasks[taskNum].getDescription());
            break;
        case "event":
        case "deadline":
        case "todo":
            HashMap<String, String> commandMap = getSubcommandMap(commandList);
            Task newTask = createTask(commandMap, mainCommand);
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
            System.out.println("I don't quite understand...");
            break;
        }
    }

    public static void Greet() {
        PrintBorder();
        System.out.println("Hello! I'm Max, your PAWsonal productivity assistant");
        System.out.println("What can I do for you to MAXimize your day?");
        PrintBorder();
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
        System.out.println("Hello from\n" + logo);

        Greet();
        setIsListening(true);

        // Init tasks subsystem
        numTasks = 0;

        // Init IO
        Scanner input = new Scanner(System.in);

        // Event driver loop to continuously listen for inputs
        while (isListening) {
            System.out.print("~$ ");
            String command = input.nextLine();
            PrintBorder();
            handleCommand(command);
            PrintBorder();
        }
    }
}
