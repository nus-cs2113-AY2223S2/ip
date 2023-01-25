import java.util.Scanner;
public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String line = "____________________________________________________________\n";
    public static void main(String[] args) {
        greetUser();
        doTask();
    }

    private static void greetUser() {
        System.out.println(logo);
        System.out.println(line + " Hello! I'm Duke\n What can I do for you?\n" + line);
    }

    private static void exitDuke() {
        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }

    private static void echoString(String input) {
        System.out.println(line + " " + input + "\n" + line);
    }

    private static void doTask() {
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                exitDuke();
                return;
            } else {
                echoString(input);
            }
        }
    }
}
