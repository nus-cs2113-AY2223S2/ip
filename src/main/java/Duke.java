import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] storage = new String[100];
        int idx = 0;
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for(int i = 0; i < 100; ++i) {
                    if (storage[i] == null) { break; }
                    System.out.println(i + 1 + ". " + storage[i]);
                }
                input = sc.nextLine();
            } else {
                storage[idx] = input;
                ++idx;
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
