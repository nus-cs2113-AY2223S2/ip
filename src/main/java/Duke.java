import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String hello = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String bye = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        String formatting_line = "____________________________________________________________\n";

        Scanner in = new Scanner (System.in);

        String[] store_values = new String[100];
        int counter = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println(hello);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                int curr_value = 0;
                String[] existing_values = Arrays.copyOf(store_values, counter);
                System.out.println(formatting_line);
                for (String value : existing_values) {
                    System.out.println((curr_value+1) + ". " + value);
                    curr_value += 1;
                }
                System.out.println(formatting_line);
            } else {
                System.out.println(formatting_line);
                System.out.println("added: " + line);
                System.out.println(formatting_line);
                //store value in line into list
                store_values[counter] = line;
                counter += 1;
            }
            line = in.nextLine();
        }
        System.out.println(bye);
    }
}
