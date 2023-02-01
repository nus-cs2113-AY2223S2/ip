import java.util.Scanner;

public class Duke {

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
        Task[] list = new Task[TOTAL_TASKS]; // Array of Tasks
        int currentNumber = 0; // Current number of tasks

        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine(); // Take in input line
            String[] lineComponents = line.split(" ", 2); // Split the input line
            String type = lineComponents[0];

            switch (type) {
            case "todo":
                currentNumber = Todo.add(line, list, currentNumber);
                System.out.println("    ____________________________________________________________\n");
                break;
            case "deadline":
                currentNumber = Deadline.add(line, list, currentNumber);
                System.out.println("    ____________________________________________________________\n");
                break;
            case "event":
                currentNumber = Event.add(line, list, currentNumber);
                System.out.println("    ____________________________________________________________\n");
                break;
            default:
                if (line.matches("list") || line.matches("bye") || line.matches("mark \\d") || line.matches("unmark \\d")) {
                    Task.executeNonAdd(line, list, currentNumber);
                    System.out.println("    ____________________________________________________________\n");
                    if (line.equals("bye")) {
                        break;
                    }
                }
            }
        }
    }
}


