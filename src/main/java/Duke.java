import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n"
        );
        ArrayList<Task> items = new ArrayList<Task>();
        String input = "";
        while (!input.equals("bye")) {
            input = sc.next();

            int order;
            if (input.equals("mark")) {
                order = sc.nextInt();
                items.get(order - 1).setMark(true);
                System.out.println("____________________________________________________________\n" + "  " + "Nice! I've marked this task as done:\n" + "[X] " + items.get(order - 1).getDescription() + "\n" + "____________________________________________________________\n");

            } else if (input.equals("unmark")) {
                order = sc.nextInt();
                items.get(order - 1).setMark(false);
                System.out.println("____________________________________________________________\n" + "  " + " OK, I've marked this task as not done yet:\n" + "[X] " + items.get(order - 1).getDescription() + "\n" + "____________________________________________________________\n");
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 1; i <= items.size(); i++) {
                    System.out.println(" " + i + ". " + items.get(i - 1).getDescription() + "[" + items.get(i - 1).getStatusIcon() + "]");
                }
                System.out.println("\n");
                System.out.println("____________________________________________________________\n");
            } else {
                Task task = new Task(input);
                items.add(task);
                System.out.println("____________________________________________________________\n" + "  " + "added: " + input + "\n" + "____________________________________________________________\n");
            }


        }


        System.out.println("____________________________________________________________\n" + "  " + "Bye. Hope to see you again soon!" + "\n" + "____________________________________________________________\n");
    }
}
