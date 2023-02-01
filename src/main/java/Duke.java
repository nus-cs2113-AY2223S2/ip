import java.util.Scanner;

public class Duke {
    public static String DIVIDER_LINE = "______________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = DIVIDER_LINE
                + "Hello! I'm Duke\n"
                + "What can i do for you\n"
                + DIVIDER_LINE;
        System.out.println(greet);
        boolean shouldContinue = true;
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        String action;
        while (shouldContinue) {
            action = in.nextLine();
            if (action.equals("bye")) {
                System.out.println(DIVIDER_LINE + "Bye. Hope to see you again soon!\n" + DIVIDER_LINE);
                shouldContinue = false;
            } else if (action.equals("list")) {
                System.out.print(DIVIDER_LINE);
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println(Integer.toString(i + 1) + ". " + tasks[i]);
                }
                System.out.println(DIVIDER_LINE);
            } else {
                tasks[taskCount] = action;
                taskCount += 1;
                System.out.println(DIVIDER_LINE + "added: " + action + "\n" + DIVIDER_LINE);
            }
        }
    }
}
