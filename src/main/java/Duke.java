import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greeting();
        boolean is_exit = false;
        while (!is_exit) {
            String messageFromUser;
            Scanner in = new Scanner(System.in);
            messageFromUser = in.nextLine();
            if (messageFromUser.equals("bye")) {
                exitGreeting();
                is_exit = true;
            } else {
                horizontalLine();
                System.out.println(messageFromUser);
                horizontalLine();
            }
        }
    }

    public static void greeting() {
        horizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    public static void exitGreeting() {
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }


    public static void horizontalLine() {
        System.out.println("________________________________________");
    }
}
