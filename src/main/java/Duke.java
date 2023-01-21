import java.util.Scanner;

public class Duke {
    static final String line = "____________________________________________________________";
    public static void main(String[] args) {
        // Greet the user
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();

            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                System.out.println(input);
            }
            System.out.println(line);
        }
        in.close();
    }
}
