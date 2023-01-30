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

    public static void main(String[] args) {
        greetUser();
        while (true) {
            userInput = scan.nextLine();
            if (userInput.equals("bye")) {
                scan.close();
                System.out.println(LINE);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.print(LINE);
                System.exit(0);
            }
            if (userInput.equals("list")) {
                System.out.println("These are the following tasks you have:");
                for (int i = 0; i < userInputCount; i++) {
                    System.out.println(LINE);
                    int index = i;
                    index++;
                    System.out.println(
                            index + ".[" + userTaskList[i].getStatusIcon() + "] " + userTaskList[i].description);
                    System.out.println(LINE);
                }
            } else {
                String[] userInputArray = userInput.split(" ");
                if (userInputArray.length > 1) {
                    if (userInputArray[0].equals("mark")) {
                        int taskIndex = Integer.parseInt(userInputArray[1]);
                        taskIndex--;
                        userTaskList[taskIndex].markAsDone();
                        System.out.print(LINE + System.lineSeparator() + "The following task has been marked done: "
                                + userTaskList[taskIndex].description + System.lineSeparator() + LINE);
                        continue;
                    } else if (userInputArray[0].equals("unmark")) {
                        int taskIndex = Integer.parseInt(userInputArray[1]);
                        taskIndex--;
                        userTaskList[taskIndex].markAsUndone();
                        System.out.print("The following task has been marked done: ");
                        System.out.println(userTaskList[taskIndex].description);
                        continue;
                    }
                }
                Task t = new Task(userInput);
                System.out.println(userInput);
                userTaskList[userInputCount] = t;
                userInputCount++;
            }
        }

    }
}
