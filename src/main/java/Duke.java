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

        boolean status = true;
        do {
            input = in.nextLine();
            String action = input.split(" ")[0];
            switch(action) {
            case "bye":
                status = false;
                break;
            case "list":
                if (taskCounter == 0) {
                    System.out.println("You are free today :)");
                } else {
                    for (int i = 0; i < taskCounter; ++i) {
                        System.out.print(i + 1 + ".");
                        tasks[i].printDescWithStatus();
                    }
                }
                printDivider();
                break;
            case "mark":
            case "unmark":
                int index = Integer.parseInt(input.split(" ")[1])-1;
                if (index < 0 || index >= taskCounter) {
                    System.out.println("Task number does not exist in list");
                    printDivider();
                    break;
                }
                if (action.equals("mark")) {
                    tasks[index].markDone();
                } else {
                    tasks[index].markUndone();
                }
                printDivider();
                break;
            default:
                tasks[taskCounter] = new Task(input);
                ++taskCounter;
                System.out.println("Added: " + input);
                printDivider();
                break;
            }
        } while (status);

        printBye();
    }
}
