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
        String echo;
        while (shouldContinue) {
            echo = in.nextLine();
            if (echo.equals("bye")) {
                System.out.println(DIVIDER_LINE + "Bye. Hope to see you again soon!\n" + DIVIDER_LINE);
                shouldContinue = false;
            } else {
                System.out.println(DIVIDER_LINE + echo + "\n" + DIVIDER_LINE);
            }
        }
    }
}
