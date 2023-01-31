import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n");
        System.out.println(" What can I do for you?\n");

        boolean isExit = false;

        while (!isExit) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if (input.equals("bye")) {
                isExit = true;
                System.out.println("______________\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("______________\n");
            } else {
                System.out.println("______________\n");
                System.out.println(input);
                System.out.println("______________\n");

                isExit = false;
            }
        }
    }
}
