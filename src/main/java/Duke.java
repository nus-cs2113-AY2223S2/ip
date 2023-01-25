import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
