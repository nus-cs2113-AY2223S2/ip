import java.util.Scanner;

public class Duke {

    public static final String LINE_BREAK = "    ____________________________________________________________";
    public static final String INDENTATION = "    ";

    public static final String HELP_PAGE = "    todo: add a new task to Duke\n" +
            "    deadline: add a new task and '/by' date to add a task with deadline\n" +
            "    event: add a new event with '/from' and '/to' duration\n" +
            "    list: list out all tasks stored\n" +
            "    help: no :D\n    Please enter command:\n";

    public static void main(String[] args) {
        System.out.println(LINE_BREAK);
        printlnWithIndentation("Hello! I'm Duke");
        printlnWithIndentation("What can I do for you?");
        System.out.println(LINE_BREAK);
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        while (true) {
            userInput = in.nextLine();
            String[] inputLine = userInput.split(" ", 2);
            String command = inputLine[0];

            if (command.equals("bye")) {
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Bye. Hope to see you again soon!");
                System.out.println(LINE_BREAK);
                // exit while loop and end program
                break;
            }

            switch (command) {
            case "list":
                // list out the tasks and status
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Here are the tasks in your list: ");
                for (int i = 0; i < taskIndex; ++i) {
                    int taskNumber = i + 1;
                    System.out.println(INDENTATION + taskNumber + "." +
                            tasks[i].toString());
                }
                System.out.println(LINE_BREAK);
                break;
            case "mark": commandMark(userInput, tasks); break;
            case "unmark": commandUnmark(userInput, tasks); break;
            case "todo":
                // command todo
                taskIndex = commandTodo(userInput, tasks, taskIndex);
                break;
            case "deadline":
                taskIndex = commandDeadline(userInput, tasks, taskIndex);
                break;
            case "event":
                taskIndex = commandEvent(userInput, tasks, taskIndex);
                break;
            case "help":
                System.out.println(HELP_PAGE);
                break;
            default:
                System.out.println(LINE_BREAK);
                printlnWithIndentation("INVALID COMMAND!");
                System.out.println(HELP_PAGE + LINE_BREAK);
            }
        }
    }

    private static int commandTodo(String userInput, Task[] tasks, int taskIndex) {
        String toAdd = userInput.substring(5);
        toAdd = toAdd.trim();
        Todo addTodo = new Todo(toAdd);
        tasks[taskIndex] = addTodo;
        taskIndex++;
        System.out.println(LINE_BREAK);
        printlnWithIndentation("Got it. I've added this task: ");
        System.out.println(INDENTATION + "  " + addTodo.toString());
        System.out.println(INDENTATION + "Now you have " +
                taskIndex + " tasks in the list. \n" + LINE_BREAK);
        return taskIndex;
    }

    private static int commandDeadline(String userInput, Task[] tasks, int taskIndex) {
        if (userInput.contains("/by")) {
            int byIndex = userInput.indexOf("/by");
            String toAddDeadline = userInput.substring(9, byIndex - 1);
            String by = userInput.substring(byIndex + 3);
            toAddDeadline = toAddDeadline.trim();
            by = by.trim();
            Deadline addDeadline = new Deadline(toAddDeadline, by);
            tasks[taskIndex] = addDeadline;
            taskIndex++;
            System.out.println(LINE_BREAK);
            printlnWithIndentation("Got it. I've added this task: ");
            System.out.println(INDENTATION + "  " + addDeadline.toString());
            System.out.println(INDENTATION + "Now you have " + taskIndex + " tasks in the list. \n" + LINE_BREAK);
        } else {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("INVALID COMMAND!");
            System.out.println(HELP_PAGE + LINE_BREAK);
        }
        return taskIndex;
    }

    private static int commandEvent(String userInput, Task[] tasks, int taskIndex) {
        if (userInput.contains("/from") & userInput.contains("/to")) {
            int fromIndex = userInput.indexOf("/from");
            int toIndex = userInput.indexOf("/to");
            String toAddEvent = userInput.substring(6, fromIndex - 1);
            String fromTime = userInput.substring(fromIndex + 5, toIndex - 1);
            String toTime = userInput.substring(toIndex + 3);
            toAddEvent = toAddEvent.trim();
            fromTime = fromTime.trim();
            toTime = toTime.trim();
            Event addEvent = new Event(toAddEvent, fromTime, toTime);
            tasks[taskIndex] = addEvent;
            taskIndex++;
            System.out.println(LINE_BREAK);
            printlnWithIndentation("Got it. I've added this task: ");
            System.out.println(INDENTATION + "  " + addEvent.toString());
            System.out.println(INDENTATION + "Now you have " + taskIndex + " tasks in the list. \n" + LINE_BREAK);
        } else {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("INVALID COMMAND!");
            System.out.println(HELP_PAGE + LINE_BREAK);
        }
        return taskIndex;
    }

    private static void commandUnmark(String userInput, Task[] tasks) {
        String toMark = userInput.substring(7);
        toMark = toMark.trim();
        int indexToMark = Integer.parseInt(toMark) - 1;
        tasks[indexToMark].setDone(false);
        printlnWithIndentation("OK, I've marked this task as not done yet: ");
        System.out.println(INDENTATION + "  [ ] " + tasks[indexToMark].getTaskDescription() + '\n' + LINE_BREAK);
    }

    private static void commandMark(String userInput, Task[] tasks) {
        String toMark = userInput.substring(5);
        toMark = toMark.trim();
        int indexToMark = Integer.parseInt(toMark) - 1;
        tasks[indexToMark].setDone(true);
        printlnWithIndentation("Nice! I've marked this task as done:");
        System.out.println(INDENTATION + "  [X] " + tasks[indexToMark].getTaskDescription() + '\n' + LINE_BREAK);
    }

    static void printlnWithIndentation(String string) {
        System.out.println(INDENTATION + string);
    }

}
