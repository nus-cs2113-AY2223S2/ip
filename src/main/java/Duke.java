import java.util.Scanner;

public class Duke {
    private static final String SEGMENT_LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static final int VALID_MARK_LENGTH = 2;
    private static final int VALID_UNMARK_LENGTH = 2;
    private static final int MINIMUM_TODO_LENGTH = 2;
    private static final int MINIMUM_DEADLINE_LENGTH = 4;
    private static final int MINIMUM_EVENT_LENGTH = 6;

    public static void printGreeting() {
        System.out.println(SEGMENT_LINE);
        System.out.println(" Hello! I'm Duke" + System.lineSeparator()
                + " What can I do for you?");
        System.out.println(SEGMENT_LINE + System.lineSeparator());
    }

    public static void printBye() {
        System.out.println(SEGMENT_LINE);
        System.out.println(" Bye! Hope to see you again soon!");
        System.out.print(SEGMENT_LINE + System.lineSeparator());
    }

    public static void runCommand(String input, Task[] tasks) {
        try {
            String[] arrayOfInput = input.split(" ");
            boolean isInputList = input.equals("list");
            if (isInputList) {
                listTasks(tasks);
            } else if (isMark(arrayOfInput)) {
                // if command is "mark <int>"
                markTask(tasks, arrayOfInput);
            } else if (isUnmark(arrayOfInput)) {
                // if command is "unmark <int>"
                unmarkTask(tasks, arrayOfInput);
            } else {
                // command is to add task
                decideTaskGroup(input, tasks, arrayOfInput);
            }
        } catch (DukeException e) {
            System.out.println(SEGMENT_LINE + System.lineSeparator());
        }
    }

    public static void listTasks(Task[] tasks) {
        System.out.println(SEGMENT_LINE);
        boolean isTaskCountZero = (Task.totalTasks == 0);
        if (isTaskCountZero) {
            System.out.println(" The list is empty!");
        } else {
            System.out.println(" Here are the tasks in your lists:");
            for (int i = 0; i < Task.totalTasks; i += 1) {
                System.out.println(" " + (i + 1) + "." + tasks[i].getFullTaskDetail());
            }
        }
        System.out.println(SEGMENT_LINE + System.lineSeparator());
    }

    public static boolean isMark(String[] input) throws DukeException {
        boolean isTwoWordInput = (input.length == VALID_MARK_LENGTH);
        boolean isFirstWordMark = input[0].equals("mark");
        if (!isFirstWordMark) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "mark"
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"mark <number>\"!");
            throw new DukeException();
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "mark <non digit chara>"
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"mark <number>\"!");
            throw new DukeException();
        }
        return true;
    }

    public static boolean isUnmark(String[] input) throws DukeException {
        // if input length (separated by " ") == 2 && first word == "unmark" && second word only contains numbers
        boolean isTwoWordInput = (input.length == VALID_UNMARK_LENGTH);
        boolean isFirstWordUnmark = input[0].equals("unmark");
        if (!isFirstWordUnmark) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "unmark"
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"unmark <number>\"!");
            throw new DukeException();
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "unmark <non digit chara>"
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"unmark <number>\"!");
            throw new DukeException();
        }
        return true;
    }

    public static boolean isStringOfInteger(String input) {
        // takes in a string and checks whether the string only contains digits characters
        char[] inputInArray = input.toCharArray();
        for (char c : inputInArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void markTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsDone();
        printMarkOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void unmarkTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsNotDone();
        printMarkOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void printMarkOrUnmark(Task[] tasks, int taskIndex) {
        System.out.println(SEGMENT_LINE);
        if (tasks[taskIndex].isDone) {
            System.out.println(" Awesome! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + tasks[taskIndex].getFullTaskDetail());
        System.out.println(SEGMENT_LINE + System.lineSeparator());
    }

    public static void decideTaskGroup(String input, Task[] tasks, String[] arrayOfInput) throws DukeException {
        boolean isInputTodo = arrayOfInput[0].equals("todo");
        boolean isInputDeadline = arrayOfInput[0].equals("deadline");
        boolean isInputEvent = arrayOfInput[0].equals("event");
        if (isInputTodo) {
            String[] todoTaskNameArray = input.split(" ", 2);
            checkValidTodo(todoTaskNameArray);
            addTodoTask(tasks, todoTaskNameArray);
        } else if (isInputDeadline) {
            checkValidDeadline(input, arrayOfInput);
            addDeadlineTask(tasks, input);
        } else if (isInputEvent) {
            checkValidEvent(input, arrayOfInput);
            addEventTask(tasks, input);
        } else {
            // if input doesn't contain any keywords
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Please provide a valid input!");
            throw new DukeException();
        }
        echoAddTasks(tasks);
        Task.incrementTotalTasks();
    }

    public static void checkValidTodo(String[] input) throws DukeException {
        if (input.length < MINIMUM_TODO_LENGTH) {
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"todo <task name>\"");
            throw new DukeException();
        }
    }

    public static void addTodoTask(Task[] tasks, String[] input) {
        tasks[Task.totalTasks] = new Todo(input[1]);
    }

    public static void checkValidDeadline(String input, String[] arrayOfInput) throws DukeException {
        if (!input.contains("/by")) {
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"deadline <task name> /by <date>\"");
            throw new DukeException();
        }
        if ( arrayOfInput.length < MINIMUM_DEADLINE_LENGTH) {
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Please provide enough arguments! "
                    + System.lineSeparator() + " Valid input format: \"deadline <task name> /by <date>\"");
            throw new DukeException();
        }
    }

    public static void addDeadlineTask(Task[] tasks, String input) {
        String[] commandInformation = input.split(" ", 2);
        String[] taskNameAndDate = commandInformation[1].split("/by", 2);
        tasks[Task.totalTasks] = new Deadline(taskNameAndDate[0].trim(), taskNameAndDate[1].trim());
    }

    public static void checkValidEvent(String input, String[] arrayOfInput) throws DukeException {
        if (!input.contains("/from") || !input.contains("/to")) {
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"event <task name> /from <date> /to <date>\"");
            throw new DukeException();
        }
        if (input.indexOf("/from") > input.indexOf("/to")) {
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Valid input format: \"event <task name> /from <date> /to <date>\"");
            throw new DukeException();
        }
        if ( arrayOfInput.length < MINIMUM_EVENT_LENGTH) {
            System.out.println(SEGMENT_LINE);
            System.out.println(" Invalid input! Please provide enough arguments! "
                    + System.lineSeparator() + " Valid input format: \"event <task name> /from <date> /to <date>\"");
            throw new DukeException();
        }
    }

    public static void addEventTask(Task[] tasks, String input) {
        String taskNameInformation = input.split(" ", 2)[1];
        String[] taskNameAndDate = taskNameInformation.split("/from", 2); // name fromTo
        String[] fromAndTo = taskNameAndDate[1].split("/to", 2); // from to
        tasks[Task.totalTasks] = new Event(taskNameAndDate[0].trim(), fromAndTo[0].trim(), fromAndTo[1].trim());
    }

    public static void echoAddTasks(Task[] tasks) {
        System.out.println(SEGMENT_LINE);
        int numberOfTasks = Task.totalTasks + 1;
        String output = " The following task has been added:" + System.lineSeparator()
                + "   " + tasks[Task.totalTasks].getFullTaskDetail() + System.lineSeparator()
                + " There is now " + numberOfTasks + " task[s] in total.";
        System.out.println(output);
        System.out.println(SEGMENT_LINE + System.lineSeparator());
    }

    public static void main(String[] args) {
        printGreeting();
        Task[] tasks = new Task[MAX_TASKS];
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            runCommand(input, tasks);
            input = in.nextLine();
        }
        printBye();
    }
}