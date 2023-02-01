import java.util.Scanner;

public class Duke {

    public static String LONG_LINE = "--------------------------------------------";
    public final int MAX_ARRAY_SIZE = 100;

    private static void printList(Task[] s, int listCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listCount; ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + '.' + s[i].toString());
        }
    }

    private static void printAddedTaskCommand(Task[] taskList, int listCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[listCount].toString());
        System.out.println("Now you have " + Integer.toString(listCount + 1) + " tasks in the list.");
    }

    private static void printGreetings() {
        System.out.println(LONG_LINE);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LONG_LINE);
    }

    private static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LONG_LINE);
    }

    public static void main(String[] args) {
        printGreetings();
        Task[] taskList = new Task[100];
        String line = "Start";
        Scanner in = new Scanner(System.in);
        int listCount = 0;
        while (!line.equals("bye")) {
            line = in.nextLine();
            if (!line.equals("bye")) {
                listCount = handleUserInputs(line, taskList, listCount);
            }
            System.out.println(LONG_LINE);
        }
        printExit();
        return;
    }

    private static void createTodo(Task[] taskList, int listCount, String input) {
        taskList[listCount] = new Todo(input);
        printAddedTaskCommand(taskList, listCount);
    }

    private static void createDeadline(Task[] taskList, int listCount, String input){
        String[] splitInput = input.split("/", 2);
        String task = splitInput[0].trim();
        String by = splitInput[1].substring(3);
        taskList[listCount] = new Deadline(task, by);
        printAddedTaskCommand(taskList, listCount);
    }

    private static void createEvent(Task[] taskList, int listCount, String input){
        String[] splitInput = input.split("/", 3);
        String task = splitInput[0].trim();
        String from = splitInput[1].substring(5).trim();
        String to = splitInput[2].substring(3);
        taskList[listCount] = new Event(task, from, to);
        printAddedTaskCommand(taskList, listCount);
    }

    private static void markTask(Task[] taskList, String input){
        int index = Integer.parseInt(input) - 1;
        if (taskList[index] == null) {
            System.out.println("No such task! Try again.");
        }
        else {
            taskList[index].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList[index].toString());
        }
    }

    private static void unmarkTask(Task[] taskList, String input){
        int index = Integer.parseInt(input) - 1;
        if (taskList[index] == null) {
            System.out.println("No such task! Try again.");
        }
        else {
            taskList[index].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList[index].toString());
        }
    }

    public static int handleUserInputs(String line, Task[] taskList, int listCount) {
        String[] cases = line.split(" ", 2);
        System.out.println(LONG_LINE);
        switch (cases[0]) {
        case "todo":
            createTodo(taskList,listCount, cases[1]);
            listCount++;
            break;
        case "deadline":
            createDeadline(taskList, listCount, cases[1]);
            listCount++;
            break;
        case "event":
            createEvent(taskList, listCount, cases[1]);
            listCount++;
            break;
        case "list":
            printList(taskList, listCount);
            break;
        case "mark":
            markTask(taskList, cases[1]);
            break;
        case "unmark":
            unmarkTask(taskList, cases[1]);
            break;
        default:
            taskList[listCount] = new Task(line);
            listCount++;
            System.out.println("added: " + line);
        }
        return listCount;
    }
}
