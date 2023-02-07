import java.util.Scanner;

public class Command {

    private static final int NOT_FOUND = -1;
    private static Scanner in = new Scanner(System.in);

    public static void linePrint() {
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
    public static void createToDo(String input, int space) {
        if (space == NOT_FOUND) {
            linePrint();
            System.out.println("Todo task cannot be empty! >:(");
            linePrint();
            return;
        }
        String description = input.substring(space + 1);
        Psyduck.addToDo(description);
        linePrint();
        System.out.println("Psyduck has added the task: " + Psyduck.getNewestTask());
        System.out.println("You now have: " + Psyduck.getTaskCount() + "tasks");
        linePrint();
    }

    public static void createEvent(String input, int space) {
        if (space == NOT_FOUND) {
            System.out.println("Event task cannot be empty! >:(");
            linePrint();
            return;
        }
        int fromPos = input.indexOf("/from");
        int toPos = input.indexOf("/to");
        if (fromPos == NOT_FOUND || toPos == NOT_FOUND) {
            linePrint();
            System.out.println("Psyduck needs a start or end date.");
            linePrint();
            return;
        }
        String description = input.substring(space + 1, fromPos);
        String from = input.substring(fromPos + 6, toPos);
        String to = input.substring(toPos + 4);
        Psyduck.addEvent(description, from, to);
        linePrint();
        System.out.println("Psyduck has added the task: " + Psyduck.getNewestTask());
        System.out.println("You now have: " + Psyduck.getTaskCount() + "tasks");
        linePrint();
    }

    public static void markTask(String input, int space) {
        try {
            int taskNum = Integer.parseInt(input.substring(space + 1));
            Psyduck.getTask(taskNum).markDone();
            linePrint();
            System.out.println("You have marked the task: " + Psyduck.getTask(taskNum).getDescription());
            linePrint();
        } catch (NullPointerException e) {
            System.out.println("Please mark a valid task.");
        }
    }

    public static void unmarkTask(String input, int space) {
        try {
            int taskNum = Integer.parseInt(input.substring(space + 1));
            Psyduck.getTask(taskNum).unmarkDone();
            linePrint();
            System.out.println("You have unmarked the task: " + Psyduck.getTask(taskNum).getDescription());
            linePrint();
        } catch (NullPointerException e) {
            System.out.println("Please unmark a valid task.");
        }
    }

    public static void createDeadline(String input, int space) {
        if (space == NOT_FOUND) {
            System.out.println("Deadline task cannot be empty! >:(");
            linePrint();
            return;
        }
        int byPos = input.indexOf("/by");
        if (byPos == NOT_FOUND) {
            linePrint();
            System.out.println("Psyduck needs a deadline.");
            linePrint();
            return;
        }
        String description = input.substring(space + 1, byPos);
        String by = input.substring(byPos + 4);
        Psyduck.addDeadline(description, by);
        linePrint();
        System.out.println("Psyduck has added the task: " + Psyduck.getNewestTask());
        System.out.println("You now have: " + Psyduck.getTaskCount() + "tasks");
        linePrint();
    }

    public static void handleInvalidCommand() {
        linePrint();
        System.out.println("Invalid command, Psyduck does not understand. (◎_◎;)");
        System.out.println("Please enter a valid command.");
        linePrint();
    }
    public static void processCommands() {
        String input = in.nextLine();
        input = input.trim();
        int space = input.indexOf(" ");
        String command;
        if (space == NOT_FOUND) {
            command = input;
        } else {
            command = input.substring(0, space);
        }
        switch (command) {
        case "bye":
            //fallthrough
        case "exit":
            Psyduck.setShouldExit(true);
            break;
        case "list":
            Psyduck.listTasks();
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
}
