import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int counter = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void printByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
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
            tasks[taskNumber - 1].markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskNumber - 1]);
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
            tasks[taskNumber - 1].markDone();
            System.out.println("Great! I have marked this task as done:");
            System.out.println(tasks[taskNumber - 1]);
            printLine();
        }
    }

    private static void printList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(tasks[i]);
        }
        printLine();
    }

    private static void addTodo(String input) {
        Todo todo = new Todo(input.substring(5));
        tasks[counter++] = todo;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.print("\t  ");
        System.out.println(todo);
        System.out.println("Now you have " + counter + " tasks in the list.");
        printLine();
    }

    private static void addDeadline(String input) {
        String[] wordDeadline = input.substring(9).split("/");
        Deadline deadline = new Deadline(wordDeadline[0].trim(), wordDeadline[1].substring(3));
        tasks[counter++] = deadline;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.print("\t  ");
        System.out.println(deadline);
        System.out.println("Now you have " + counter + " tasks in the list.");
        printLine();
    }

    private static void addEvent(String input) {
        String[] wordEvent = input.substring(5).split("/", 3);
        Event event = new Event(wordEvent[0].trim(), wordEvent[1].substring(5), wordEvent[2].substring(3));
        tasks[counter++] = event;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.print("\t  ");
        System.out.println(event);
        System.out.println("Now you have " + counter + " tasks in the list.");
        printLine();
    }

    private static void runDuke() {
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark")) {
                markAsDone(input);
            } else if (input.startsWith("unmark")) {
                markAsUndone(input);
            } else if (input.startsWith("todo")) {
                addTodo(input);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            }
            input = in.nextLine();
        }
    }


    public static void main(String[] args) {
        printWelcomeMessage();
        runDuke();
        printByeMessage();
    }
}

