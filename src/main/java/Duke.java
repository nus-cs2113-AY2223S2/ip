import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("bye")) {
                // terminate
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                // echo
                System.out.println(input);
            }
        }
    }
}
