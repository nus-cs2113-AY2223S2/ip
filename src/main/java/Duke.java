import java.util.Scanner;

public class Duke {
    private static String[] taskList = new String[100];
    private static int taskCount = 0;

    private static void print(String s) {
        System.out.printf("%s%n%s%n%s%n", Messages.LINE.TEXT, s, Messages.LINE.TEXT);
    }

    private static void printTaskList() {
        System.out.println(Messages.LINE.TEXT);
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("%d. %s%n", i + 1, taskList[i]);
        }
        System.out.println(Messages.LINE.TEXT);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        print(Messages.START.TEXT);
        while (isRunning) {
            String command = in.nextLine();
            switch (command) {
            case "list":
                printTaskList();
                break;
            case "bye":
                isRunning = false;
                break;
            default:
                taskList[taskCount++] = command;
                print(Messages.ADD.TEXT + command);
            }
        }
        print(Messages.EXIT.TEXT);
    }
}
