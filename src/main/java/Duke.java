import java.util.Scanner;

public class Duke {
    private static boolean isListening;
    private static Task[] tasks = new Task[100];
    private static int numTasks;


    public static void setKeepAlive(boolean keepListening) {
        Duke.isListening = keepListening;
    }

    public static void PrintBorder() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    public static void Exit() {
        System.out.print("Goodbye! Thank you for using Duke.\n");
    }

    public static void handleCommand(String command) {
        // Update the keepAlive flag
        String[] commandList = command.split(" ");
        String cmd = commandList[0];
        switch (cmd) {
        case "Bye":
        case "bye":
        case "exit":
            setKeepAlive(false);
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
            if (commandList.length < 2) {
                System.out.println("Missing argument: Task number");
                return;
            }
            int taskNum = Integer.parseInt(commandList[1]) - 1; // Convert to 0-idx
            // Update its value
            tasks[taskNum].markAsDone();
            System.out.println("Okay, marking this task as done: ");
            System.out.println(tasks[taskNum].getDescription());
            break;
        case "unmark":
            if (commandList.length < 2) {
                System.out.println("Missing argument: Task number");
                return;
            }
            taskNum = Integer.parseInt(commandList[1]) - 1; // Convert to 0-idx
            // Update its value
            tasks[taskNum].markAsUndone();
            System.out.println("Okay, setting this task as undone: ");
            System.out.println(tasks[taskNum].getDescription());
            break;
        default:
            // Default behaviour is to add the command
            // Insert command into tasklist
            String desc = "";
            for (String s : commandList) {
                if (desc.length() > 0) {
                    desc = desc + " " + s;
                } else {
                    desc = s;
                }
            }
            Task t = new Task(desc);
            tasks[numTasks] = t;
            ++numTasks;
            System.out.println("Okay, adding: \"" + desc + "\"!");
            break;
        }
    }

    public static void Greet() {
        PrintBorder();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        PrintBorder();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Greet();
        setKeepAlive(true);

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
