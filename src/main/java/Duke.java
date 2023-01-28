import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static final String LINE_PARTITION = "    ____________________________________________________________\n";
    public static final String FAREWELL = LINE_PARTITION +
            "     Bye. Hope to see you again soon!\n" +
            LINE_PARTITION;
    public static final String GREET = LINE_PARTITION +
            "     Hello! I'm kimo\n" +
            "     What can I do for you?\n" +
            LINE_PARTITION;

    public static void printAdd(String prompt) {
        System.out.print(LINE_PARTITION +
                "     added: " + prompt + "\n" +
                LINE_PARTITION);
    }
    public static void printTaskNotFound(int taskNum) {
        System.out.print(LINE_PARTITION +
                "Task number " + taskNum + " does not exist, please try again." +
                LINE_PARTITION);
    }
    public static void printMark(Task[] prompt, int number) {
        prompt[number].setDone(true);
        System.out.print(LINE_PARTITION +
                "     Great job! I will mark this task as done: \n" +
                "     [X] " + prompt[number].getName() + "\n" +
                LINE_PARTITION);
    }
    public static void printUnmark(Task[] prompt, int number) {
        prompt[number].setDone(false);
        System.out.print(LINE_PARTITION +
                "     Alright, I have marked this task as undone: \n" +
                "     [ ] " + prompt[number].getName() + "\n" +
                LINE_PARTITION);
    }

    public static void printList(Task[] taskList) {
        System.out.print(LINE_PARTITION +
                "     These are the tasks in your list: ");
        int counter = 1;
        for (Task task : taskList) {
            System.out.println("     " + counter + ".[" + task.getStatus() + "] " + task.getName());
            counter++;
        }
        System.out.print(LINE_PARTITION);

    }

    public static void main(String[] args) {

        System.out.print(GREET);

        Task[] taskList = new Task[100];
        String userInput;
        int listLength = 0;
        Scanner in = new Scanner(System.in);

        inputLoop:
        while (true) {
            userInput = in.nextLine();
            switch (userInput) {
            case "list":
                printList(Arrays.copyOf(taskList, listLength));
                break;

            case "bye":
                break inputLoop;

            default:
                if (userInput.matches("mark \\d+") ) { // check whether format of input is "mark (integer)"
                    String[] inputArray = userInput.split(" ");
                    int taskNum = Integer.parseInt(inputArray[1]);
                    if (taskNum - 1 < (listLength)) {  // check whether integer provided exceeds length of taskList
                        printMark(taskList, taskNum - 1);
                    } else {
                        printTaskNotFound(taskNum);
                    }
                }
                else if (userInput.matches("unmark \\d+")) { // check whether format of input is "unmark (integer)"
                    String[] inputArray = userInput.split(" ");
                    int taskNum = Integer.parseInt(inputArray[1]);
                    if (taskNum - 1 < (listLength)) { // check whether integer provided exceeds length of taskList
                        printUnmark(taskList, taskNum - 1);
                    } else {
                        printTaskNotFound(taskNum);
                    }
                }
                else {
                    Task task = new Task(userInput);
                    taskList[listLength] = task;
                    listLength++;
                    printAdd(userInput);
                }
            }
        }

        System.out.println(FAREWELL);
    }
}

/*
String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
System.out.println("Hello from\n" + logo);
*/
