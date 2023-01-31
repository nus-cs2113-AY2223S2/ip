import java.util.Scanner;

public class Psyduck {
    private static boolean shouldExit = false;

    private static Task[] tasks = new Task[100];

    private static int taskCount = 0;

    private static Scanner in = new Scanner(System.in);

    public static void addToDo(String description) {
        ToDo newTask = new ToDo(description);
        tasks[taskCount] = newTask;
        taskCount++;
    }

    public static void createToDo(String input, int space) {
        if (space == -1) {
            linePrint();
            System.out.println("Todo task cannot be empty! >:(");
            linePrint();
            return;
        }
        String description = input.substring(space + 1);
        addToDo(description);
        linePrint();
        System.out.println("Psyduck has added the task: " + tasks[taskCount - 1]);
        linePrint();
    }

    public static void addDeadline(String description, String by) {
        Deadline newTask = new Deadline(description, by);
        tasks[taskCount] = newTask;
        taskCount++;
    }

    public static void createDeadline(String input, int space) {
        if (space == -1) {
            System.out.println("Deadline task cannot be empty! >:(");
            linePrint();
            return;
        }
        int byPos = input.indexOf("/by");
        if (byPos == -1) {
            linePrint();
            System.out.println("Psyduck needs a deadline.");
            System.out.println("Remember to use the <deadline> <description> </by> <date> format for your input");
            linePrint();
            return;
        }
        String description = input.substring(space + 1, byPos);
        String by = input.substring(byPos + 4);
        addDeadline(description, by);
        linePrint();
        System.out.println("Psyduck has added the task: " + tasks[taskCount - 1]);
        linePrint();
    }

    public static void addEvent(String description, String from, String to) {
        Event newTask = new Event(description, from, to);
        tasks[taskCount] = newTask;
        taskCount++;
    }

    public static void createEvent(String input, int space) {
        if (space == -1) {
            System.out.println("Event task cannot be empty! >:(");
            linePrint();
            return;
        }
        int fromPos = input.indexOf("/from");
        int toPos = input.indexOf("/to");
        if (fromPos == -1 || toPos == -1) {
            linePrint();
            System.out.println("Psyduck needs a start or end date.");
            System.out.println("Remember to use the <deadline> <description> </from> <date> </to> <date> format for your input");
            linePrint();
            return;
        }
        String description = input.substring(space + 1, fromPos);
        String from = input.substring(fromPos + 6, toPos);
        String to = input.substring(toPos + 4);
        addEvent(description, from, to);
        linePrint();
        System.out.println("Psyduck has added the task: " + tasks[taskCount - 1]);
        linePrint();
    }

    public static void listTasks() {
        linePrint();
        for (int i = 0; i < taskCount; i++) {
            System.out.print(Integer.toString(i + 1) + ".");
            System.out.println(tasks[i]);
        }
        linePrint();
    }

    public static void markTask(String input, int space) {
        int taskNum = Integer.parseInt(input.substring(space + 1));
        tasks[taskNum - 1].markDone();
        linePrint();
        System.out.println("You have marked the task: " + tasks[taskNum - 1].getDescription());
        linePrint();
    }

    public static void unmarkTask(String input, int space) {
        int taskNum = Integer.parseInt(input.substring(space + 1));
        tasks[taskNum - 1].unmarkDone();
        linePrint();
        System.out.println("You have unmarked the task: " + tasks[taskNum - 1].getDescription());
        linePrint();
    }

    public static void linePrint() {
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static void greet() {
        linePrint();
        System.out.println("( ´ ▽ ` )ﾉ Hi I am Psyduck! How can I help you?");
        linePrint();
    }

    public static void handleInvalidCommand() {
        linePrint();
        System.out.println("Invalid command, Psyduck does not understand. (◎_◎;)");
        System.out.println("Please enter a valid command.");
        linePrint();
    }

    public static void processCommands() {
        String description;
        int taskNum;
        String input = in.nextLine();
        input = input.trim();
        int space = input.indexOf(" ");
        String command;
        if (space == -1) {
            command = input;
        } else {
            command = input.substring(0, space);
        }
        switch (command) {
        case "bye":
            //fallthrough
        case "exit":
            shouldExit = true;
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            markTask(input, space);
            break;
        case "unmark":
            unmarkTask(input, space);
            break;
        case "todo":
            createToDo(input, space);
            break;
        case "deadline":
            createDeadline(input, space);
            break;
        case "event":
            createEvent(input, space);
            break;
        default:
            handleInvalidCommand();
        }
    }

    public static void main(String[] args) {
        greet();
        do {
            processCommands();
        } while (!shouldExit);
        System.out.println("Bye see you soon! (⌒ー⌒)ﾉ");
        linePrint();
    }
}
