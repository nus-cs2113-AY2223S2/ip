import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static final String LINE_PARTITION = "    ____________________________________________________________\n";
    public static final String FAREWELL = LINE_PARTITION +
            "\t  Bye. Hope to see you again soon!\n" +
            LINE_PARTITION;
    public static final String GREET = LINE_PARTITION +
            "\t  Hello! I'm kimo\n" +
            "\t  What can I do for you?\n" +
            LINE_PARTITION;

    public static void printAdd(String task) {
        System.out.print(LINE_PARTITION +
                "\t  added: " + task + "\n" +
                LINE_PARTITION);
    }
    public static void printTaskNotFound(int taskNum) {
        System.out.print(LINE_PARTITION +
                "\t  Task number " + taskNum + " does not exist, please try again.\n" +
                LINE_PARTITION);
    }
    public static void printMark(Task[] taskList, int number) {
        taskList[number].setDone(true);
        System.out.print(LINE_PARTITION +
                "\t  Great job! I will mark this task as done: \n" +
                "\t  [X] " + taskList[number].getName() + "\n" +
                LINE_PARTITION);
    }
    public static void printUnmark(Task[] taskList, int taskNum) {
        taskList[taskNum].setDone(false);
        System.out.print(LINE_PARTITION +
                "\t  Alright, I have marked this task as undone: \n" +
                "\t  [ ] " + taskList[taskNum].getName() + "\n" +
                LINE_PARTITION);
    }

    public static void printList(Task[] taskList) {
        System.out.println(LINE_PARTITION +
                "\t  These are the tasks in your list: ");
        int counter = 1;
        for (Task task : taskList) {
            System.out.println("\t  " + counter + ".[" + task.getStatus() + "] " + task.getName());
            counter++;
        }
        System.out.print(LINE_PARTITION);
    }

    public static void takeUserInput() {
        Task[] taskList = new Task[100];
        String userInput;
        int listLength = 0;
        Scanner in = new Scanner(System.in);
        while (true) {
            userInput = in.nextLine();
            switch (userInput) {
            case "list":
                printList(Arrays.copyOf(taskList, listLength));
                break;

            case "bye":
                return;

            default:
                if (userInput.matches("mark \\d+") ) { // check if format of input is "mark (integer)"
                    String[] inputArray = userInput.split(" ");
                    int taskNum = Integer.parseInt(inputArray[1]);
                    if (taskNum - 1 < (listLength) && taskNum > 0) {
                        // check if integer provided exceeds length of taskList & ensure taskNum is positive
                        printMark(taskList, taskNum - 1);
                    } else {
                        printTaskNotFound(taskNum);
                    }
                }

                else if (userInput.matches("unmark \\d+")) { // check if format of input is "unmark (integer)"
                    String[] inputArray = userInput.split(" ");
                    int taskNum = Integer.parseInt(inputArray[1]);
                    if (taskNum - 1 < (listLength) && taskNum > 0) {
                        // check if integer provided exceeds length of taskList & ensure taskNum is positive
                        printUnmark(taskList, taskNum - 1);
                    } else {
                        printTaskNotFound(taskNum);
                    }
                }

                else { // default add to list case
                    Task task = new Task(userInput);
                    taskList[listLength] = task;
                    listLength++;
                    printAdd(userInput);
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.print(GREET);
        takeUserInput();
        System.out.println(FAREWELL);
    }
}

/*
String logo =
        " ____        _        \n" +
        "|  _ \\ _   _| | _____ \n" +
        "| | | | | | | |/ / _ \\\n" +
        "| |_| | |_| |   <  __/\n" +
        "|____/ \\__,_|_|\\_\\___|\n";
System.out.println("Hello from\n" + logo);
*/
