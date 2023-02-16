import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task>tasks = new ArrayList<>();
    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printDivider() {
        String DIVIDER = "____________________________________________________";
        System.out.println(DIVIDER);
    }

    public static void markTask(String input) {
        int taskNumber = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(taskNumber).markDone();
    }

    public static void unmarkTask(String input) {
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(taskNumber).umarkDone();
    }

    public static void printList() {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task output : tasks) {
            System.out.println(count + "." + output);
            count++;
        }
        printDivider();
    }

    public static void createTodo(String input) {
        tasks.add(new Todo(input.substring(5)));
        printDivider();
        System.out.println("Got it. I've added this tasks:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printDivider();
    }

    public static void createDeadline(String input) {
        int byIndex = input.indexOf("/by");
        tasks.add(new Deadline(input.substring(9, byIndex), input.substring(byIndex + 4)));
        printDivider();
        System.out.println("Got it. I've added this tasks:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printDivider();
    }

    public static void createEvent(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        tasks.add(new Event(input.substring(6, fromIndex), input.substring(fromIndex + 6, toIndex), input.substring(toIndex + 4)));
        printDivider();
        System.out.println("Got it. I've added this tasks:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printDivider();
    }

    public static void main(String[] args) {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + LOGO);
        printDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        printDivider();

        while(true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                printExit();
                printDivider();
                break;
            } else if (input.startsWith("mark")) {
                markTask(input);
                printDivider();
            } else if (input.startsWith("unmark")) {
                unmarkTask(input);
                printDivider();
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("todo")) {
                createTodo(input);
            } else if (input.startsWith("deadline")) {
                createDeadline(input);
            } else if (input.startsWith("event")) {
                createEvent(input);
            } else {
                System.out.print("Sorry, could you please repeat what you just said\n" );
            }
        }
    }
}
