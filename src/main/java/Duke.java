import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] storage = new Task[100];
        int idx = 0;
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for(int i = 0; i < 100; ++i) {
                    if (storage[i] == null) { break; }
                    System.out.println(i + 1 + "." + storage[i].getStatusIcon() + " " + storage[i].description);
                }
                input = sc.nextLine();
            } else if (input.contains("unmark")) {
                String[] separated = input.split(" ");
                int number = Integer.parseInt(separated[1]);
                if (number <= 0 || number > 100 || storage[number - 1] == null ) {
                    System.out.println("Please unmark only valid tasks");
                    input = sc.nextLine();
                } else {
                    storage[number - 1].unmarkDone();
                    System.out.println(" " + storage[number - 1].getStatusIcon() + " " + storage[number - 1].description);
                    input = sc.nextLine();
                }
            } else if (input.contains("mark")) {
                String[] separated = input.split(" ");
                int number = Integer.parseInt(separated[1]);
                if (number <= 0 || number > 100 || storage[number - 1] == null ) {
                    System.out.println("Please mark only valid tasks");
                    input = sc.nextLine();
                } else {
                    storage[number - 1].markAsDone();
                    System.out.println("  " + storage[number - 1].getStatusIcon() + " " + storage[number - 1].description);
                    input = sc.nextLine();
                }
            }
            else {
                Task tX = new Task(input);
                storage[idx] = tX;
                ++idx;
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
