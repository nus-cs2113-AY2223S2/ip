import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            String line = in.nextLine();
            System.out.println(line);
            if (line.equals("bye")) {
                break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
