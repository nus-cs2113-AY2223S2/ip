import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
        dukeAddList(input);
    }

    public static void dukeAddList(Scanner inputScanner) {
        Set<String> list = new HashSet<>();
        while (true) {
            String echo = inputScanner.nextLine();
            if (echo.equals("list")) {
                int counter = 1;
                for (String string : list) {
                    System.out.println(counter + ". " + string);
                    counter += 1;
                }
            } else if (echo.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else {
                list.add(echo);
            }
        }
    }
    public static void dukeEcho(Scanner inputScanner) {
        while (true) {
            String echo = inputScanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(echo);
        }
    }
}
