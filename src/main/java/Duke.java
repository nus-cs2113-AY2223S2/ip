import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int listSize = 0;

    public static void printDivider() {
        String divider = "\t____________________________________";
        System.out.println(divider);
    }

    public static void printLogo() {
        String logo = "\t ____        _        \n"
                    + "\t|  _ \\ _   _| | _____ \n"
                    + "\t| | | | | | | |/ / _ \\\n"
                    + "\t| |_| | |_| |   <  __/\n"
                    + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    public static void printWelcome() {
        String welcomeMessage = "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?";

        printDivider();
        printLogo();
        System.out.println(welcomeMessage);
        printDivider();
    }

    public static void printExit() {
        String exitMessage = "\tBye. Hope to see you again soon!";

        System.out.println(exitMessage);
        printDivider();
    }

    public static void printTasks() {
        printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < listSize; i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList[i]);
        }
        printDivider();
    }

    public static void addTodo(String inputMessage) {
        Task todo = new Todo(inputMessage);
        if (listSize >= 100) {
            System.out.println("List size exceeded!");
            return;
        }
        taskList[listSize] = todo;
        listSize++;

        String addedMessage = "\tGot it. I've added this task:\n"
                + "\t  " + todo.toString();
        String sizeMessage = "\tNow you have " + listSize + " tasks in the list.";

        printDivider();
        System.out.println(addedMessage);
        System.out.println(sizeMessage);
        printDivider();
    }

    public static void addDeadline(String inputMessage, String by) {
        Task deadline = new Deadline(inputMessage, by);
        if (listSize >= 100) {
            System.out.println("List size exceeded!");
            return;
        }
        taskList[listSize] = deadline;
        listSize++;

        String addedMessage = "\tGot it. I've added this task:\n"
                + "\t  " + deadline.toString();
        String sizeMessage = "\tNow you have " + listSize + " tasks in the list.";

        printDivider();
        System.out.println(addedMessage);
        System.out.println(sizeMessage);
        printDivider();
    }

    public static void addEvent(String inputMessage, String from, String to) {
        Task event = new Event(inputMessage, from, to);
        if (listSize >= 100) {
            System.out.println("List size exceeded!");
            return;
        }
        taskList[listSize] = event;
        listSize++;

        String addedMessage = "\tGot it. I've added this task:\n"
                + "\t  " + event.toString();
        String sizeMessage = "\tNow you have " + listSize + " tasks in the list.";

        printDivider();
        System.out.println(addedMessage);
        System.out.println(sizeMessage);
        printDivider();
    }

    public static void markTask(int taskNum) {
        Task currentTask = taskList[taskNum - 1];
        if (taskNum > listSize || taskNum <= 0) {
            System.out.println("\tNo such task exists! Please try again");
            return;
        }
        currentTask.markDone();

        String markMessage = "\tNice! I've marked this task as done:\n"
                + "\t  " + currentTask.toString();
        printDivider();
        System.out.println(markMessage);
        printDivider();
    }

    public static void unmarkTask(int taskNum) {
        Task currentTask = taskList[taskNum - 1];
        if (taskNum > listSize || taskNum <= 0) {
            System.out.println("\tNo such task exists! Please try again");
            return;
        }
        currentTask.markUndone();

        String unmarkMessage = "\tOK, I've marked this task as not done yet:\n"
                + "\t  " + currentTask.toString();

        printDivider();
        System.out.println(unmarkMessage);
        printDivider();
    }

    public static void main(String[] args) {

        printWelcome();
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            String inputString = input.nextLine();
            String[] inputArray = inputString.split(" ", 2);
            String command = inputArray[0];

            switch (command) {
            case "todo":
                String todoDetails = inputArray[1];
                addTodo(todoDetails);
                break;

            case "deadline":
                if (!inputArray[1].contains("/by")) {
                    System.out.println("\tWrong format! Please include /by.");
                    break;
                }

                String[] deadlineDetails = inputArray[1].split(" /by ", 2);
                String deadlineDescription = deadlineDetails[0];
                String by = deadlineDetails[1];
                addDeadline(deadlineDescription, by);
                break;

            case "event":
                if (!inputArray[1].contains("/from") && !inputArray[1].contains("/to")) {
                    System.out.println("\tWrong format! Please include /from and /to.");
                    break;
                }

                String[] eventDetails = inputArray[1].split(" /from | /to ", 3);
                String eventDescription = eventDetails[0];
                String from = eventDetails[1];
                String to = eventDetails[2];
                addEvent(eventDescription, from, to);
                break;

            case "list":
                printTasks();
                break;

            case "mark":
                int taskNum = Integer.parseInt(inputArray[1]);
                markTask(taskNum);
                break;

            case "unmark":
                int unmarkTaskNum = Integer.parseInt(inputArray[1]);
                unmarkTask(unmarkTaskNum);
                break;

            case "bye":
                printExit();
                System.exit(0);
                break;

            default:
                break;
            }
        }
    }
}
