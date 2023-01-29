import java.util.Scanner;

public class Duke {

    public static Task[] list = new Task[100];
    public static int counter = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printWelcomeMessage();
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                addToList();
            } else if (input.startsWith("mark")) {
                markAsDone(input);
            } else if (input.startsWith("unmark")) {
                markAsUndone(input);
            } else {
                addToList(input);
            }
            input = in.nextLine();
        }
        printByeMessage();
    }

    private static void printByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

    private static void addToList(String input) {
        list[counter] = new Task(input);
        counter++;
        printLine();
        System.out.println("added : " + input);
        printLine();
    }

    private static void markAsUndone(String input) {
        printLine();
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > counter) {
            System.out.println("Sorry, this task does not exist.");
            printLine();
        } else {
            list[taskNumber - 1].markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + list[taskNumber - 1].getStatusIcon() + "] " + list[taskNumber - 1].description);
            printLine();
        }
    }

    private static void markAsDone(String input) {
        printLine();
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > counter) {
            System.out.println("Sorry, this task does not exist.");
            printLine();
        } else {
            list[taskNumber - 1].markDone();
            System.out.println("Great! I have marked this task as done:");
            System.out.println("[" + list[taskNumber - 1].getStatusIcon() + "] " + list[taskNumber - 1].description);
            printLine();
        }
    }

    private static void addToList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + "[" + list[i].getStatusIcon() + "] " + list[i].description);
        }
        printLine();
    }

    private static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }
}
