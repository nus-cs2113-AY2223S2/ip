import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        String[] items = new String[100];
        int index = 0;
        int itemCount = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String HORIZONTAL_LINE = "____________________________________________________________";

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        do {
            input = in.nextLine();
            System.out.println(HORIZONTAL_LINE);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                for (int i = 0; i < itemCount; i++) {
                    System.out.println((i + 1) + ". " + items[i]);
                }
            } else {
                items[index] = input;
                index++;
                itemCount++;
                System.out.println("added: " + input);
            }
            System.out.println(HORIZONTAL_LINE);
        } while (!input.equals("bye"));
    }
}
