import java.util.Scanner;
import java.util.List;

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

        String array[] = new String[100];
        int counter = 0; // number of items in the list
        boolean isExit = false;

        while (!isExit) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if (input.equals("bye")) {
                isExit = true;
                System.out.println("______________\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("______________\n");
            } else if (input.equals("list")) {
                System.out.println("______________\n");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + array[i]);
                }
                System.out.println("______________\n");
                isExit = false;

            } else {
                System.out.println("added: " + input);
                array[counter] = input;
                counter++;
            }
        }
    }
}
