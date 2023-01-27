import java.util.Scanner;

public class Duke {
    static final String LINE = "\t____________________________________________________________";
    static final String COMMAND_BYE = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";
    static final String COMMAND_EVENT = "event";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DEADLINE = "deadline";
    static Task[] tasks = new Task[100];
    static int textCount = 0;

    public static void doCommandGreet() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm Percy.");
        System.out.println("\tHow can I help you today?\n");
        System.out.println(LINE);
    }

    public static void doCommandBye() {
        System.out.println(LINE);
        System.out.println("\tBye! Remember to finish your tasks.\n");
        System.out.println(LINE);
    }

    public static void doCommandMark(int taskNum) {
        tasks[--taskNum].setStatus(true);
        System.out.println(LINE);
        System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
        System.out.println("\t  " + tasks[taskNum].getTaskNameAndStatus());
        System.out.println(LINE);
    }

    public static void doCommandUnmark(int taskNum) {
        tasks[--taskNum].setStatus(false);
        System.out.println(LINE);
        System.out.println("\tOh, ok. Task " + (taskNum + 1) + " has been marked as \"incomplete\":");
        System.out.println("\t  " + tasks[taskNum].getTaskNameAndStatus());
        System.out.println(LINE);
    }

    public static void doCommandList() {
        System.out.println(LINE);
        System.out.println("\tHere are your tasks:");
        int count = 1;
        for (int index = 0; index < textCount; index++) {
            System.out.print("\t" + count + ".");
            System.out.println(tasks[index]);
            count++;
        }
        System.out.println(LINE);
    }

    public static void doCommandTodo(String taskName) {
        tasks[textCount] = new Todo(taskName);
        textCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks[textCount - 1]);
        System.out.println("\t" + "Now you have " + textCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandDeadline(String taskName, String taskDeadline) {
        tasks[textCount] = new Deadline(taskName, taskDeadline);
        textCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks[textCount - 1]);
        System.out.println("\t" + "Now you have " + textCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandEvent(String eventName, String eventDetailsPartOne, String eventDetailsPartTwo) {
        tasks[textCount] = new Event(eventName, eventDetailsPartOne, eventDetailsPartTwo);
        textCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks[textCount - 1]);
        System.out.println("\t" + "Now you have " + textCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void handleUserCommand(String userCommand) {
        String[] extractFirstWord = userCommand.split(" ", 2);
        String firstWord = extractFirstWord[0];
        if (firstWord.equals(COMMAND_MARK)) {
            int taskNum = Integer.parseInt(extractFirstWord[1]);
            doCommandMark(taskNum);
        } else if (firstWord.equals(COMMAND_UNMARK)) {
            int taskNum = Integer.parseInt(extractFirstWord[1]);
            doCommandUnmark(taskNum);
        } else if (firstWord.equals(COMMAND_LIST)) {
            doCommandList();
        } else if (firstWord.equals(COMMAND_BYE)) {
            doCommandBye();
        } else if (firstWord.equals(COMMAND_TODO)) {
            String taskName = (extractFirstWord[1]);
            doCommandTodo(taskName);
        } else if (firstWord.equals(COMMAND_DEADLINE)) {
            int index = extractFirstWord[1].indexOf("/by");
            String taskName = extractFirstWord[1].substring(0, index);
            String taskDeadline = extractFirstWord[1].substring(index + 4);
            doCommandDeadline(taskName, taskDeadline);
        } else if (firstWord.equals(COMMAND_EVENT)) {
            int indexOfEventDetailsPartOne = extractFirstWord[1].indexOf("/from");
            int indexOfEventDetailsPartTwo = extractFirstWord[1].indexOf("/to");
            String eventName = extractFirstWord[1].substring(0, indexOfEventDetailsPartOne);
            String eventDetailsPartOne = extractFirstWord[1].substring(indexOfEventDetailsPartOne + 6, indexOfEventDetailsPartTwo - 1);
            String eventDetailsPartTwo = extractFirstWord[1].substring(indexOfEventDetailsPartTwo + 4);
            doCommandEvent(eventName, eventDetailsPartOne, eventDetailsPartTwo);
        } else {
            System.out.println("Please input a valid command!");
        }
    }

    public static void main(String[] args) {
        doCommandGreet();
        Scanner in = new Scanner(System.in);
        String userCommand;
        do {
            userCommand = in.nextLine();
            handleUserCommand(userCommand);
        } while (!userCommand.equals(COMMAND_BYE));
    }
}
