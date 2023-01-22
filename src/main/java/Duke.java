import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        while (true) {
            line = in.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if ("list".equalsIgnoreCase(line)) {
                System.out.println("____________________________________________________________");
                int index = 1;
                for (String item : tasks) {
                    System.out.println(index + ". " + item);
                    index++;
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks.add(line);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
