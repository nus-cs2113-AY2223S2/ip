import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskSize = 0;

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
        String welcomeMessage = "\tHello! I'm Duke\n\tWhat can I do for you?";

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

    public static void addTasks(String inputMessage) {
        Task newTask = new Task(inputMessage);
        tasks[taskSize] = newTask;
        taskSize++;
        String addedMessage = "\tadded: " + inputMessage;

        printDivider();
        System.out.println(addedMessage);
        printDivider();
    }

    public static void printTasks() {
        printDivider();
        for (int i = 0; i < taskSize; i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks[i].toString());
        }
        printDivider();
    }

    public static void markTask(int taskNum) {
        Task currentTask = tasks[taskNum - 1];
        currentTask.markDone();

        String markMessage = "\tNice! I've marked this task as done:\n" + "\t" + currentTask.toString();
        printDivider();
        System.out.println(markMessage);
        printDivider();
    }

    public static void unmarkTask(int taskNum) {
        Task currentTask = tasks[taskNum - 1];
        currentTask.markUndone();

        String unmarkMessage = "\tOK, I've marked this task as not done yet:" + "\t" + currentTask.toString();

        printDivider();
        System.out.println(unmarkMessage);
        printDivider();
    }

    public static void main(String[] args) {

        printWelcome();
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            String inputString = input.nextLine();
            String[] inputArray = inputString.split(" ");
            String command = inputArray[0];

            switch (command) {
            case "bye":
                printExit();
                System.exit(0);
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

            default:
                addTasks(inputString);
            }
        }
    }
}
