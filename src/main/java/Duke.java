import java.util.Scanner;

public class Duke {
    private static int userInputCount = 0;
    private static final String LINE = "--------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static Task[] userTaskList = new Task[100];
    private static String userInput;
    private static Scanner scan = new Scanner(System.in);

    public static void greetUser() {
        System.out.println(LINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("How can I help you?");
        System.out.println(LINE);
    }

    public static void goodbyeUser() {
        scan.close();
        System.out.println(LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(LINE);
        System.exit(0);
    }

    public static void listTasks() {
        if (userInputCount == 0) {
            System.out.println(LINE);
            System.out.println("You have no tasks added!");
            System.out.println(LINE);
        } else {
            System.out.println("These are the following tasks you have:");
            for (int i = 0; i < userInputCount; i++) {
                System.out.println(LINE);
                int index = i;
                index++;
                System.out.println(
                        index + ".[" + userTaskList[i].getStatusIcon() + "] " + userTaskList[i].description);
                System.out.println(LINE);
            }
        }
    }

    public static void markTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            userTaskList[taskIndex].markAsDone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked done: [X] "
                    + userTaskList[taskIndex].description + System.lineSeparator() + LINE);
        } catch (Exception e) {
            System.out.println(
                    LINE + System.lineSeparator() + "ERROR OCCURED: Please enter a valid numerical index of the task!"
                            + System.lineSeparator() + LINE);
        }

    }

    public static void unmarkTask(String[] userInputArray) {
        try {
            int taskIndex = Integer.parseInt(userInputArray[1]);
            taskIndex--;
            userTaskList[taskIndex].markAsUndone();
            System.out.println(LINE + System.lineSeparator() + "The following task has been marked undone: [ ] "
                    + userTaskList[taskIndex].description + System.lineSeparator() + LINE);
        } catch (Exception e) {
            System.out.println(
                    LINE + System.lineSeparator() + "ERROR OCCURED: Please enter a valid numerical index of the task!"
                            + System.lineSeparator() + LINE);
        }
    }

    public static void processUserInput() {
        userInput = scan.nextLine();
        if (userInput.equals("/bye")) {
            goodbyeUser();
        }
        if (userInput.equals("/list")) {
            listTasks();
        } else {
            String[] userInputArray = userInput.split(" ");
            if (userInputArray[0].equals("/mark")) {
                markTask(userInputArray);
                return;
            } else if (userInputArray[0].equals("/unmark")) {
                unmarkTask(userInputArray);
                return;
            }
            Task t = new Task(userInput);
            System.out.println(LINE);
            System.out.println("Added task: " + userInput);
            System.out.println(LINE);
            userTaskList[userInputCount] = t;
            userInputCount++;

        }
    }

    public static void main(String[] args) {
        greetUser();
        while (true) {
            processUserInput();
        }
    }
}
