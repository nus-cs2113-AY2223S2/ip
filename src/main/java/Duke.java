import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (true) {
            String echo = input.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(echo);
        }
    }
}
