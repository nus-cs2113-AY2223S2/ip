import java.util.Scanner;

public class Duke {
    /* immutable Strings below
     *
     *
     */
    public static final String GREETING = " Hello! I'm Duke! What can I do for you?";

    public static final String INVALID = " Invalid command!";
    public static final String GOODBYE = " Bye. Hope to see you again soon!";
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final int MAX_TASKS = 101;

    /* Storage and variables below
     *
     *
     */
    private static int indexBaseOne;
    private static Task[] tasks;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String[] commandWords;

    private static String commandWord;

    private static String commandParameters;


    public static void main(String[] args) {
        greet();
        initTasks();

        do {
            decodeUserInputs();

            resolveUserInputs();

            lineBreak();


        } while (true);
    }

    private static void resolveUserInputs() {
        if (commandWord.equals("bye")) {
            goodbye();
        }

        switch (commandWord) {
            case "list":
                printTaskList();

                break;
            case "mark": {
                markTaskDone();

                break;
            }
            case "unmark": {
                MarkTaskNotDone();
                break;
            }
            case "todo":
                addToDo();

                break;
            case "deadline":
                addDeadline();

                break;
            case "event":
                addEvent();

                break;
            default:
                System.out.println(INVALID);
                System.out.println( "input: " + commandWord + "," + commandParameters);
                break;
        }
    }

    private static void addEvent() {
        commandParameters = commandWords[1];
        final int indexOfFrom = commandParameters.indexOf("/from");
        final int indexOfTo = commandParameters.indexOf("/to");
        tasks[indexBaseOne] = new Event(commandParameters.substring(0, indexOfFrom),
                commandParameters.substring(indexOfFrom + 6, indexOfTo),
                commandParameters.substring(indexOfTo + 4));
        printLastAddedTask();
        indexBaseOne += 1;
    }

    private static void addDeadline() {
        commandParameters = commandWords[1];
        final int indexOfBy = commandParameters.indexOf("/by");
        tasks[indexBaseOne] = new Deadline(commandParameters.substring(0, indexOfBy),
                commandParameters.substring(indexOfBy + 4));
        printLastAddedTask();
        indexBaseOne += 1;
    }

    private static void addToDo() {
        commandParameters = commandWords[1];
        tasks[indexBaseOne] = new ToDo(commandParameters);
        printLastAddedTask();
        indexBaseOne += 1;
    }

    private static void MarkTaskNotDone() {
        commandParameters = commandWords[1];
        int pointer = Integer.parseInt(commandParameters);
        tasks[pointer].setNotDone();
        System.out.println("Ok I've marked this task as not done:\n"
                + tasks[pointer].getTaskStatus());
    }

    private static void markTaskDone() {
        commandParameters = commandWords[1];
        int pointer = Integer.parseInt(commandParameters);
        tasks[pointer].setDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + tasks[pointer].getTaskStatus());
    }

    private static void printTaskList() {
        for (int i = 1; i < indexBaseOne; i++) {
            System.out.println(i + ": " + tasks[i].getTaskStatus());
        }
    }

    private static void printLastAddedTask() {
        System.out.println("added: " + tasks[indexBaseOne].getTaskStatus());
    }

    private static void decodeUserInputs() {
        commandWords = SCANNER.nextLine().trim().split(" ", 2);
        commandWord = commandWords[0];
    }

    /* Methods below
     * note: can declare method after calling them as long as they are in same class
     * since calling any method technically does Class.method()
     *
     */
    public static void lineBreak() {
        System.out.println(LINE_DIVIDER);
    }

    public static void greet() {
        lineBreak();
        System.out.println(GREETING);
        lineBreak();
    }

    public static void goodbye() {
        System.out.println(GOODBYE);
        lineBreak();
        System.exit(0);
    }

    public static void initTasks() {
        tasks = new Task[MAX_TASKS];
        indexBaseOne = 1;
    }


}