import java.util.Scanner;

public class Duke {

    public static final int MAX_ARRAY_SIZE = 100;
    public static final String GREET_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    public static final String LINE = "____________________________________________________________\n";
    public static final String MARK_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";

    public static boolean isProgramRunning = true;

    private static Task[] tasks = new Task[MAX_ARRAY_SIZE];

    private static int commandCount = 0;

    public static void main(String[] args) {
        greeting();
        Scanner in = new Scanner(System.in);

        while (isProgramRunning) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                exit();
            } else if (command.equals("list")) {
                printList();
            } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                markAndUnmarkHandler(command);
            } else if (command.startsWith("todo ")) {
                initTodoTask(command);
            } else if (command.startsWith("deadline ")) {
                initDeadlineTask(command);
            } else if (command.startsWith("event ")) {
                initEventTask(command);
            } else {
                initNewGeneralTask(command);
            }
        }
    }

    private static void initEventTask(String command) {
        command = command.replace("event ", "");
        String[] stringSplit = command.split(" /from ");
        if (isInvalidString(stringSplit)) {
            initNewGeneralTask("event " + command);
            return;
        }
        String[] fromAndTo = stringSplit[1].split(" /to ");
        if (isInvalidString(fromAndTo)) {
            initNewGeneralTask("event " + command);
            return;
        }
        tasks[commandCount] = new Event(stringSplit[0], fromAndTo[0], fromAndTo[1]);
        addSpecialTaskMessage();
        commandCount++;
    }

    private static boolean isInvalidString(String[] stringSplit) {
        return stringSplit.length != 2;
    }

    private static void initDeadlineTask(String command) {
        command = command.replace("deadline ", "");
        String[] stringSplit = command.split(" /by ");
        if (isInvalidString(stringSplit)) {
            initNewGeneralTask("deadline " + command);
            return;
        }
        tasks[commandCount] = new Deadline(stringSplit[0], stringSplit[1]);
        addSpecialTaskMessage();
        commandCount++;
    }

    private static void initTodoTask(String command) {
        String todo = command.replace("todo ", "");
        tasks[commandCount] = new Todo(todo);
        addSpecialTaskMessage();
        commandCount++;
    }

    private static void addSpecialTaskMessage() {
        System.out.println(LINE + tasks[commandCount].addTaskMessage() + "Now you have " + (commandCount + 1)
                + " tasks in the list." + System.lineSeparator() + LINE);
    }

    private static void initNewGeneralTask(String command) {
        echoCommand(command);
        tasks[commandCount] = new Task(command);
        commandCount++;
    }

    private static void echoCommand(String command) {
        System.out.println(LINE + "added: " + command + System.lineSeparator() + LINE);
    }

    private static void markAndUnmarkHandler(String command) {
        String[] words = command.split(" ");
        if (words.length == 2) {
            int indexOfMarking = isNumeric(words[1]);
            if (!isValidMarking(command, indexOfMarking)) {
                initNewGeneralTask(command);
                return;
            }
            createMarkOrUnmark(command, words, indexOfMarking);
        } else {
            initNewGeneralTask(command);
        }
    }

    private static void createMarkOrUnmark(String command, String[] words, int indexOfMarking) {
        indexOfMarking--; // change to 0-index
        tasks[indexOfMarking].setDone(words[0]);
        System.out.print(LINE);
        if (command.startsWith("mark ")) {
            System.out.println(MARK_MESSAGE);
        } else {
            System.out.println(UNMARK_MESSAGE);
        }
        System.out.println("  " + tasks[indexOfMarking].toString() + System.lineSeparator() + LINE);
    }

    private static boolean isValidMarking(String command, int indexOfMarking) {
        if (indexOfMarking == -1) {
            return false;
        }
        return true;
    }

    private static void exit() {
        System.out.print(LINE + EXIT_MESSAGE + System.lineSeparator() + LINE); // Duke saying goodbye and closes program
        isProgramRunning = false;
    }

    private static void printList() {
        System.out.print(LINE);
        for (int i = 0; i < commandCount; ++i) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
        System.out.println(LINE);
    }

    private static void greeting() {
        System.out.println(LINE + GREET_MESSAGE);
    }

    private static int isNumeric(String strNum) {
        // Referenced from https://www.baeldung.com/java-check-string-number
        int index = 0;
        if (strNum == null) {
            return -1;
        }
        try {
            index = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        if (index > commandCount) {
            return -1;
        }
        return index; // Returns 1-index of Task or -1 if the input does not fit a number or is bigger than array size
    }
}
