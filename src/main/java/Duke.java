import java.util.Scanner;


public class Duke {
    public static void printHorizontalLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    public static void listing(Task[] listOfTasks, int currentNumberIndex) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < currentNumberIndex; ++i) {
            int counter = i + 1;
            System.out.print("     " + counter + "." + listOfTasks[i].taskLabel + listOfTasks[i].getStatusIcon() + " ");
            System.out.println(listOfTasks[i].description);
        }
    }

    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke \n"
                + "     What can I do for you? \n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        String line;
        final int TOTAL_TASKS = 100; // Total number of tasks
        Task[] tasksList = new Task[TOTAL_TASKS]; // Array of Tasks
        int currentNumber = 0; // Current number of tasks

        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine(); // Take in input line
            String[] lineComponents = line.split(" ", 2); // Split the input line
            String type = lineComponents[0];

            switch (type) {
            case "todo":
                currentNumber = Todo.add(line, tasksList, currentNumber);
                printHorizontalLine();
                break;
            case "deadline":
                currentNumber = Deadline.add(line, tasksList, currentNumber);
                printHorizontalLine();
                break;
            case "event":
                currentNumber = Event.add(line, tasksList, currentNumber);
                printHorizontalLine();
                break;
            case "list":
                printHorizontalLine();
                listing(tasksList, currentNumber);
                printHorizontalLine();
                break;
            case "bye":
                printHorizontalLine();
                System.out.println("     Bye. Hope to see you again soon!");
                printHorizontalLine();
                break;
            default:
                if (line.matches("mark \\d") || line.matches("unmark \\d")) {
                    Task.markOrUnmark(line, tasksList, currentNumber);
                    printHorizontalLine();
                    break;
                }
            }
            if (line.matches("bye")) {
                break;
            }
        }
    }
}



