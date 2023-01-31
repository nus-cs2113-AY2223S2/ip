import java.util.Scanner;
public class Duke {

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        String logo = " ____\n"
                + "|    \\ ___ _ _ ___\n"
                + "|  |  | .'| | | -_|\n"
                + "|____/|__,|\\_/|___|\n";

        printDivider();
        System.out.print(logo);
        System.out.println("Hi, I'm Dave!\n"
                + "What can I do for you?");
        printDivider();
    }

    private static void printTaskAdded(Task[] tasks, int taskCounter) {
        System.out.println("Got it. I've added this task:\n  " + tasks[taskCounter -1]
                + "\nNow you have " + taskCounter + " tasks in the list.");
        printDivider();
    }
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void main(String[] args) {

        printGreeting();

        String input;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        input = in.nextLine();
        while (!input.equals("bye")) {
            String action = input.split(" ")[0];
            switch (action) {
            case "list":
                if (taskCounter == 0) {
                    System.out.println("You are free today :)");
                } else {
                    for (int i = 0; i < taskCounter; ++i) {
                        System.out.print(i + 1 + ".");
                        System.out.println(tasks[i]);
                    }
                }
                printDivider();
                break;
            case "mark":
            case "unmark":
                int indexOfTask = Integer.parseInt(input.split(" ")[1]) - 1;
                if (indexOfTask < 0 || indexOfTask >= taskCounter) {
                    System.out.println("Task number does not exist in list");
                    printDivider();
                    break;
                }
                if (action.equals("mark")) {
                    tasks[indexOfTask].markDone();
                } else {
                    tasks[indexOfTask].markUndone();
                }
                printDivider();
                break;
            case "todo":
                tasks[taskCounter] = new ToDo(input.substring(action.length() + 1));
                ++taskCounter;
                printTaskAdded(tasks, taskCounter);
                break;
            case "deadline":
                int indexOfBy = input.indexOf(" /by ");
                tasks[taskCounter] = new Deadline(input.substring(action.length() + 1, indexOfBy)
                        , input.substring(indexOfBy + (" /by ").length()));
                ++taskCounter;
                printTaskAdded(tasks, taskCounter);
                break;
            case "event":
                int indexOfFrom = input.indexOf(" /from ");
                int indexOfTo = input.indexOf(" /to ");
                tasks[taskCounter] = new Event(input.substring(action.length() + 1, indexOfFrom)
                        , input.substring(indexOfFrom + (" /from ").length(), indexOfTo)
                        , input.substring(indexOfTo + (" /to ").length()));
                ++taskCounter;
                printTaskAdded(tasks, taskCounter);
                break;
            default:
                System.out.println("Not a valid command, please try again");
                printDivider();
            }

            input = in.nextLine();
        }

        printBye();
    }


}
