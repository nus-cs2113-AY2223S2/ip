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
        String[] status = new String[100];
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
                    System.out.println((curr_value+1) + ".[" + status[curr_value] + "] " + value);
                    curr_value += 1;
                }
                System.out.println(formatting_line);
            } else if (line.startsWith("mark ")) {
                int length = line.length();
                String item_to_mark = line.substring(5, length);
                int num_to_mark = Integer.parseInt(item_to_mark);
                //mark the item as complete
                status[num_to_mark-1] = "X";
                System.out.println(formatting_line +
                        "Nice! I've marked this task as done: \n" +
                        "[X] " + store_values[num_to_mark-1] + "\n" +
                        formatting_line);
            } else if (line.startsWith("unmark ")) {
                int length = line.length();
                String item_to_mark = line.substring(7, length);
                int num_to_mark = Integer.parseInt(item_to_mark);
                status[num_to_mark-1] = " ";
                System.out.println(formatting_line +
                        "OK, I've marked this task as not done yet: \n" +
                        "[ ] " + store_values[num_to_mark-1] + "\n" +
                        formatting_line);
            } else {
                System.out.println(formatting_line);
                System.out.println("added: " + line);
                System.out.println(formatting_line);
                //store value in line into list
                store_values[counter] = line;
                status[counter] = " ";
                counter += 1;
            }
            line = in.nextLine();
        }
        System.out.println(bye);
    }
}
