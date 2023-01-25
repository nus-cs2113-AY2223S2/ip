import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(welcome);
        while(true) {
            Scanner s = new Scanner(System.in);  // Create a Scanner object
            String echo = s.nextLine();  // Read user input
            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("*****************************");
                System.out.println(echo);
                System.out.println("*****************************");
            }
            }
        }
}
