import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String greet ="____________________________________________________________\n" +
                " Hello! I'm kimo\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greet);
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("    ____________________________________________________________\n" +
                    "     " + userInput + "\n" +
                    "    ____________________________________________________________\n ");
            userInput = in.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
