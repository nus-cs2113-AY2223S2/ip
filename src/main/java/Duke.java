// Java program to read data of various types using Scanner class.
import java.util.Scanner;
public class Duke {
    public static final int TASKS_COUNT = 100;
    private static Task[] tasks = new Task[TASKS_COUNT];
    public static void printWelcomeMessage() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Elzi, your dog!\n" +
                " What can I do for my master?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
    }
    public static void printLine() {
        System.out.print("___________________________******___________________________\n");
    }
    public static void printList(int taskCounter) {
        System.out.println("Your current tasks are as follows: ");
        for (int index = 0; index < taskCounter; index += 1) {
            System.out.println((index + 1) + "." + "[" +
                    tasks[index].getStatusIcon() + "]" + " " +
                    tasks[index].getDescription());
        }
    }
    private static void readInput() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();

        int taskCounter = 0;

        while (!input.equals("bye")) {

            String[] inputs = input.split(" ");
            String command  = inputs[0];

            printLine();

            switch (command) {
            case "list":
                printList(taskCounter);
                break;
            case "mark":
                int taskIndex = Integer.parseInt(inputs[1]);
                tasks[taskIndex-1].markAsDone();
                System.out.println("I have marked this task as done");
                printList(taskCounter);
                break;
            case "unmark":
                taskIndex = Integer.parseInt(inputs[1]);
                tasks[taskIndex-1].markAsNotDone();
                System.out.println("I have unmarked this task");
                printList(taskCounter);
                break;
            default:
                Task t = new Task(input);
                tasks[taskCounter] = t; taskCounter += 1;
                System.out.println("added: " + input);
            }

            printLine();

            input = sc.nextLine();
        }

    }
    public static void main(String[] args) {
        printWelcomeMessage();
        readInput();
        // Print end text line
        printLine();
    }
}
