
// import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(line + "\n");
            line = in.nextLine();
        }

        in.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
