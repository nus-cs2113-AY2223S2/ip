import java.util.Scanner;

public class Duke {
    public static final int MAX_TASKS = 100;
    private static int taskCount = 0;
    static Task[] tasks = new Task[MAX_TASKS];

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
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
        System.out.println("Type: [todo] [something], and the system will add a new todo item to your list");
        System.out.println("Type: [event] [something] /from [when] /to [when], and the system will add an event and the timing");
        System.out.println("Type: [deadline] [something] /by[when], and the system will add a deadline");
        System.out.println("Type: [mark] [number], and the system will mark the item of the number as done");
        System.out.println("Type: [unmark] [number], and the system will unmark the item of the number.");
        System.out.println("Type: bye, to say goodbye to Duke!");
        System.out.println("Hope it helps!! woof a nice day ੯•໒꒱❤︎");
        printLine();
    }

    public static void startProgram() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        final String[] split = s.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case "bye":
            goodBye();
            return;
        case "list":
            printListOfTasks();
            break;
        case "todo":
            try {
                addTodo(commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a todo cannot be empty.");
                System.out.println("Please try to add todo again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "deadline":
            try {
                addDeadline(commandArgs);
            } catch (NoDescriptionException e) {
                System.out.println("WOOFS!!! The description of a deadline cannot be empty.");
                System.out.println("Please try to add deadline again υ´• ﻌ •`υ");
                printLine();
            } catch (FormatException e) {
                System.out.println("WOOFS!!! The format of entering deadline is incorrect.");
                System.out.println("Please try to add deadline again υ´• ﻌ •`υ");
                printLine();
            }
            break;
        case "event":
            addEvent(commandArgs);
            break;
        case "mark":
            markTask(commandArgs);
            break;
        case "unmark":
            unmarkTask(commandArgs);
            break;
        default:
            info();
        }

        startProgram();
    }

    private static void unmarkTask(String commandArgs) {
        final int unmarkId = Integer.parseInt(commandArgs) - 1;
        if (!tasks[unmarkId].isDone) {
            System.out.println("This task hasn't been marked as done yet ∪･ω･∪");
        } else {
            tasks[unmarkId].markAsNotDone();
            System.out.println("I've unmarked this task ∪･ω･∪:");
            System.out.println(tasks[unmarkId]);
        }
        printLine();
    }

    private static void markTask(String commandArgs) {
        final int markId = Integer.parseInt(commandArgs) - 1;
        if (tasks[markId].isDone) {
            System.out.println("This task has already been marked as done ੯•໒꒱❤︎");
        } else {
            tasks[markId].markAsDone();
            System.out.println("I've marked this task as done ੯•໒꒱❤︎:");
            System.out.println(tasks[markId]);
        }
        printLine();
    }

    private static void addEvent(String commandArgs) {
        final int indexOfFrom = commandArgs.indexOf("/from");
        final int indexOfTo = commandArgs.indexOf("/to");
        String eventDescription = commandArgs.substring(0, indexOfFrom).trim();
        String from = commandArgs.substring(indexOfFrom, indexOfTo).trim().replace("/from", "");
        String to = commandArgs.substring(indexOfTo, commandArgs.length()).trim().replace("/to", "");
        addTask(new Event(eventDescription, from, to));
        printLine();
        System.out.println("Got it. I've added this task: \n" + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in your list.");
        printLine();
    }

    private static void addDeadline(String commandArgs) throws NoDescriptionException, FormatException{
        final int indexOfDeadline = commandArgs.indexOf("/by");
        if (indexOfDeadline == -1) {
            throw new FormatException();
        }
        String deadlineDescription = commandArgs.substring(0, indexOfDeadline).trim();
        if (deadlineDescription.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        String deadline = commandArgs.substring(indexOfDeadline, commandArgs.length()).trim().replace("/by", "");
        if (deadline.trim().length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(new Deadline(deadlineDescription, deadline));
        printLine();
        System.out.println("Got it. I've added this task: \n" + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in your list.");
        printLine();
    }

    private static void addTodo(String commandArgs) throws NoDescriptionException {
        if ((commandArgs.trim()).length() == 0) {
            throw new NoDescriptionException();
        }
        addTask(new Todo(commandArgs));
        printLine();
        System.out.println("Got it. I've added this task: \n" + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in your list.");
        printLine();
    }

    private static void printListOfTasks() {
        for (int i = 0; i < taskCount; i += 1) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(tasks[i]);
        }
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        startProgram();
    }
}
