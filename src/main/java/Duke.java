import java.util.Scanner;
public class Duke {
    private static final String SEGMENT_LINE = "____________________________________________________________";
    public static final int MAX_TASKS = 100;

    public static void printGreeting() {
        String greetingText = " Hello! I'm Duke" + System.lineSeparator()
                + " What can I do for you?";
        System.out.println(SEGMENT_LINE);
        System.out.println(greetingText);
        System.out.println(SEGMENT_LINE + System.lineSeparator());
    }

    public static void printBye() {
        String goodbyeText = " Bye! Hope to see you again soon!";
        System.out.println(SEGMENT_LINE);
        System.out.println(goodbyeText);
        System.out.print(SEGMENT_LINE + System.lineSeparator());
    }

    public static void runCommand(String input, Task[] tasks) {
        if (input.equals("list")) {
            listTasks(tasks);
        } else {
            String[] arrayOfInput = input.split(" ");
            if (arrayOfInput.length == 2 && arrayOfInput[0].equals("mark")
                    && isStringInteger(arrayOfInput[1])) {
                // if command is "mark <digit>"
                tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsDone();
                printMarkStatement(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
            } else if (arrayOfInput.length == 2 && arrayOfInput[0].equals("unmark")
                    && isStringInteger(arrayOfInput[1])) {
                // if command is "unmark <digit>"
                tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsNotDone();
                printMarkStatement(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
            } else {
                addTask(input, tasks, arrayOfInput);
            }
        }
    }

    public static void addTask(String input, Task[] tasks, String[] arrayOfInput) {
        if (arrayOfInput[0].equals("todo")) {
            String taskName = "";
            for (int i = 1; i < arrayOfInput.length; i += 1) {
                taskName += arrayOfInput[i];
                taskName += " ";
            }
            tasks[Task.totalTasks] = new Todo(taskName.trim());
        } else if (arrayOfInput[0].equals("deadline")) {
            String taskName = "";
            String deadline = "";
            int arrayOfInputIndex = 1;
            while (arrayOfInputIndex < arrayOfInput.length && !arrayOfInput[arrayOfInputIndex].equals("/by")) {
                taskName += arrayOfInput[arrayOfInputIndex];
                taskName += " ";
                arrayOfInputIndex += 1;
            }
            arrayOfInputIndex += 1;
            while (arrayOfInputIndex < arrayOfInput.length) {
                deadline += arrayOfInput[arrayOfInputIndex];
                deadline += " ";
                arrayOfInputIndex += 1;
            }
            tasks[Task.totalTasks] = new Deadline(taskName.trim(), deadline.trim());
        } else if (arrayOfInput[0].equals("event")) {
            String taskName = "";
            String startDateTime = "";
            String endDateTime = "";
            int arrayOfInputIndex = 1;
            while (arrayOfInputIndex < arrayOfInput.length && !arrayOfInput[arrayOfInputIndex].equals("/from")) {
                taskName += arrayOfInput[arrayOfInputIndex];
                taskName += " ";
                arrayOfInputIndex += 1;
            }
            arrayOfInputIndex += 1;
            while (arrayOfInputIndex < arrayOfInput.length && !arrayOfInput[arrayOfInputIndex].equals("/to")) {
                startDateTime += arrayOfInput[arrayOfInputIndex];
                startDateTime += " ";
                arrayOfInputIndex += 1;
            }
            arrayOfInputIndex += 1;
            while (arrayOfInputIndex < arrayOfInput.length) {
                endDateTime += arrayOfInput[arrayOfInputIndex];
                endDateTime += " ";
                arrayOfInputIndex += 1;
            }
            tasks[Task.totalTasks] = new Event(taskName.trim(), startDateTime.trim(), endDateTime.trim());
        } else {
            // if input is just the task itself, without deadline / event / todo
            tasks[Task.totalTasks] = new Task(input);
        }
        echoAddTasks(tasks);
        Task.incrementTotalTasks();
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

    public static void listTasks(Task[] tasks) {
        System.out.println(SEGMENT_LINE);
        if (Task.totalTasks == 0) {
            System.out.println(" The list is empty!");
        } else {
            System.out.println(" Here are the tasks in your lists:");
            for (int i = 0; i < Task.totalTasks; i += 1) {
                System.out.println(" " + (i + 1) + "." + tasks[i].getFullTaskDetail());
            }
        }
        System.out.println(SEGMENT_LINE + System.lineSeparator());
    }

    public static boolean isStringInteger(String input) {
        // takes in a string and checks whether the string only contains digits
        char[] inputInArray = input.toCharArray();
        for (int i = 0; i < inputInArray.length; i += 1) {
            if (!Character.isDigit(inputInArray[i])) {
                return false;
            }
        }
        return true;
    }

    public static void printMarkStatement(Task[] tasks, int taskIndex) {
        System.out.println(SEGMENT_LINE);
        if (tasks[taskIndex].isDone) {
            System.out.println(" Awesome! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + tasks[taskIndex].getFullTaskDetail());
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
