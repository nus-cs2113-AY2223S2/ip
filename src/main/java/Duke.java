import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.length() > 0) {
                System.out.printf("\t%s\n", line);
            }
            line = in.nextLine();
        }

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
