import java.util.Scanner;
public class Duke {
    private static final String SEGMENT_LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;

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
        String[] arrayOfInput = input.split(" ");
        boolean isInputList = input.equals("list");
        if (isInputList) {
            listTasks(tasks);
        } else if (isMark(arrayOfInput)) {
            // if command is "mark <digit>"
            markTask(tasks, arrayOfInput);
        } else if (isUnmark(arrayOfInput)) {
            // if command is "unmark <digit>"
            unmarkTask(tasks, arrayOfInput);
        } else {
            addTask(input, tasks, arrayOfInput);
        }
    }

    public static void listTasks(Task[] tasks) {
        System.out.println(SEGMENT_LINE);
        boolean isTaskCountZero = Task.totalTasks == 0;
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

    public static boolean isMark(String[] arrayOfInput) {
        boolean isTwoWordInput = arrayOfInput.length == 2;
        boolean isFirstWordMark = arrayOfInput[0].equals("mark");
        boolean isSecondWordNumber = isStringInteger(arrayOfInput[1]);
        return (isTwoWordInput && isFirstWordMark && isSecondWordNumber);
    }

    public static boolean isUnmark(String[] arrayOfInput) {
        // if input length (separated by " " is 2) && first word == "unmark" && second word only contains numbers
        boolean isTwoWordInput = arrayOfInput.length == 2;
        boolean isFirstWordUnmark = arrayOfInput[0].equals("unmark");
        boolean isSecondWordNumber = isStringInteger(arrayOfInput[1]);
        return (isTwoWordInput &&isFirstWordUnmark && isSecondWordNumber);
    }

    public static boolean isStringInteger(String input) {
        // takes in a string and checks whether the string only contains digits
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
        printMarkOrUnmarkStatement(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void unmarkTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsNotDone();
        printMarkOrUnmarkStatement(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void printMarkOrUnmarkStatement(Task[] tasks, int taskIndex) {
        System.out.println(SEGMENT_LINE);
        if (tasks[taskIndex].isDone) {
            System.out.println(" Awesome! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + tasks[taskIndex].getFullTaskDetail());
        System.out.println(SEGMENT_LINE + System.lineSeparator());
    }

    public static void addTask(String input, Task[] tasks, String[] arrayOfInput) {
        boolean isInputTodo = arrayOfInput[0].equals("todo");
        boolean isInputDeadline = arrayOfInput[0].equals("deadline");
        boolean isInputEvent = arrayOfInput[0].equals("event");
        if (isInputTodo) {
            addTodoTask(tasks, input);
        } else if (isInputDeadline) {
            addDeadlineTask(tasks, arrayOfInput);
        } else if (isInputEvent) {
            addEventTask(tasks, arrayOfInput);
        } else {
            // if input is just the task itself without keyword
            tasks[Task.totalTasks] = new Task(input);
        }
        echoAddTasks(tasks);
        Task.incrementTotalTasks();
    }

    public static void addTodoTask(Task[] tasks, String input) {
        String[] commandTaskNameArray = input.split(" ", 2);
        tasks[Task.totalTasks] = new Todo(commandTaskNameArray[1]);
    }

    public static void addDeadlineTask(Task[] tasks, String[] arrayOfInput) {
        String taskName = "";
        String deadlineDate = "";
        int arrayOfInputIndex = 1;
        while (isDeadlineTaskName(arrayOfInput, arrayOfInputIndex)) {
            taskName = String.join(" ", taskName, arrayOfInput[arrayOfInputIndex]);
            arrayOfInputIndex += 1;
        }
        arrayOfInputIndex += 1;
        while (isWithinArrayLength(arrayOfInput, arrayOfInputIndex)) {
            // words after /by are for the dateline
            deadlineDate = String.join(" ", deadlineDate, arrayOfInput[arrayOfInputIndex]);
            arrayOfInputIndex += 1;
        }
        tasks[Task.totalTasks] = new Deadline(taskName.trim(), deadlineDate.trim());
    }

    public static boolean isDeadlineTaskName(String[] arrayOfInput, int arrayOfInputIndex) {
        boolean isNotByWord = !arrayOfInput[arrayOfInputIndex].equals("/by");
        return isWithinArrayLength(arrayOfInput, arrayOfInputIndex) && isNotByWord;
        // used as a condition for a while loop to iterate through words before "/by"
    }

    public static boolean isWithinArrayLength(String[] arrayOfInput, int arrayOfInputIndex) {
        return arrayOfInputIndex < arrayOfInput.length;
    }

    public static void addEventTask(Task[] tasks, String[] arrayOfInput) {
        String taskName = "";
        String startDateTime = "";
        String endDateTime = "";
        int arrayOfInputIndex = 1;
        while (isEventTaskName(arrayOfInput, arrayOfInputIndex)) {
            taskName = String.join(" ", taskName, arrayOfInput[arrayOfInputIndex]);
            arrayOfInputIndex += 1;
        }
        arrayOfInputIndex += 1;
        while (isEventStartDate(arrayOfInput, arrayOfInputIndex)) {
            startDateTime = String.join(" ", startDateTime, arrayOfInput[arrayOfInputIndex]);
            arrayOfInputIndex += 1;
        }
        arrayOfInputIndex += 1;
        while (isWithinArrayLength(arrayOfInput, arrayOfInputIndex)) {
            // words after /to are for the endDateTime
            endDateTime = String.join(" ", endDateTime, arrayOfInput[arrayOfInputIndex]);
            arrayOfInputIndex += 1;
        }
        tasks[Task.totalTasks] = new Event(taskName.trim(), startDateTime.trim(), endDateTime.trim());
    }

    public static boolean isEventTaskName(String[] arrayOfInput, int arrayOfInputIndex) {
        boolean isNotFromWord = !arrayOfInput[arrayOfInputIndex].equals("/from");
        return isWithinArrayLength(arrayOfInput, arrayOfInputIndex) && isNotFromWord;
        // used as a condition for a while loop to iterate through words before "/from"
    }

    public static boolean isEventStartDate(String[] arrayOfInput, int arrayOfInputIndex) {
        boolean isNotToWord = !arrayOfInput[arrayOfInputIndex].equals("/to");
        return isWithinArrayLength(arrayOfInput, arrayOfInputIndex) && isNotToWord;
        // used as a condition for a while loop to iterate through words between "/from" and "/to"
    }

    public static void echoAddTasks(Task[] tasks) {
        System.out.println(SEGMENT_LINE);
        int numberOfTasks = Task.totalTasks + 1;
        String output = " The following task has been added:" +  System.lineSeparator()
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