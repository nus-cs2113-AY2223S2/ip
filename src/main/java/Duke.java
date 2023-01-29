import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static final int MAX_TASKS = 100;
    private static int listId = 0;
    static Task[] list = new Task[MAX_TASKS];

    public static void addTask(Task t){
        list[listId] = t;
        listId++;
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void greet() {
        printLine();
        System.out.println("Hello! I'm Duke  U ´ᴥ` U\n" + "What can I do for you?");
        printLine();
    }
    public static void goodBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!ﾉ~");
        printLine();
    }
    public static void info() {
        printLine();
        System.out.println("This command is not valid, please read through the info and try again :)");
        System.out.println("Example 1: ");
        System.out.println("Type: [todo] [something], and the system will add a new todo item to your list");
        System.out.println("Type: [event] [something] /from [when] /to [when], and the system will add an event and the timing");
        System.out.println("Type: [deadline] [something] /by[when], and the system will add a deadline");
        System.out.println("Type: [mark] [number], and the system will mark the item of the number as done");
        System.out.println("Type: [unmark] [number], and the system will unmark the item of the number.");
        System.out.println("Type: bye, to say goodbye to Duke!");
        System.out.println("Hope it helps!! woof a nice day ੯•໒꒱❤︎");
        printLine();
    }
    public static void echo() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        // check input
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        final String[] split = s.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        // System.out.println(commandArgs + " " + commandType);
        switch (commandType) {
        case "bye":
            goodBye();
            return;
        case "list":
            for (int i = 0; i < listId; i += 1) {
                System.out.print(i+1);
                System.out.print(". ");
                System.out.println(list[i]);
            }
            printLine();
            break;
        case "todo":
            addTask(new Todo(commandArgs));
            printLine();
            System.out.println("Added \n" +list[listId-1]);
            printLine();
            break;
        case "deadline":
            final int indexOfDeadline = commandArgs.indexOf("/by");
            String deadlineDescription = commandArgs.substring(0,indexOfDeadline).trim();
            String deadline = commandArgs.substring(indexOfDeadline, commandArgs.length()).trim().replace("/by", "");
            addTask(new Deadline(deadlineDescription, deadline));
            printLine();
            System.out.println("Added \n" + list[listId-1]);
            printLine();
            break;
        case "event":
            final int indexOfFrom = commandArgs.indexOf("/from");
            final int indexOfTo = commandArgs.indexOf("/to");
            String eventDescription = commandArgs.substring(0,indexOfFrom).trim();
            String from = commandArgs.substring(indexOfFrom, indexOfTo).trim().replace("/from", "");
            String to = commandArgs.substring(indexOfTo, commandArgs.length()).trim().replace("/to", "");
            addTask(new Event(eventDescription, from, to));
            printLine();
            System.out.println("Added \n" + list[listId-1]);
            printLine();
            break;
        case "mark":
            final int markId = Integer.parseInt(commandArgs)-1;
            if (list[markId].isDone) {
                System.out.println("This task has already been marked as done ੯•໒꒱❤︎");
            } else {
                list[markId].markAsDone();
                System.out.println("I've marked this task as done ੯•໒꒱❤︎:");
                System.out.println(list[markId]);
            }
            printLine();
            break;
        case "unmark":
            final int unmarkId = Integer.parseInt(commandArgs)-1;
            if (!list[unmarkId].isDone) {
                System.out.println("This task hasn't been marked as done yet ∪･ω･∪");
            } else {
                list[unmarkId].markAsNotDone();
                System.out.println("I've unmarked this task ∪･ω･∪:");
                System.out.println(list[unmarkId]);
            }
            printLine();
            break;
        default:
            info();
        }

        echo();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}
