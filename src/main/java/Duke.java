import java.util.ArrayList;
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
        ArrayList<String> listItems = new ArrayList<>();
        while (true) {

            Scanner s = new Scanner(System.in);  // Create a Scanner object
            String echo = s.nextLine();  // Read user input

            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (echo.equals("list")) {
                System.out.println("*****************************");
                for (int i = 0; i < listItems.size(); i++) {
                    System.out.println(i + 1 + ". " + listItems.get(i));
                }
                System.out.println("*****************************");
            } else {
                listItems.add(echo);
                System.out.println("added: " + echo);
            }
        }
    }
}
