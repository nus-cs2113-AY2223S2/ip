import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    final static String HORIZONTAL_RULE = "____________________________________________________________\n";

    public static void greeting() {
        System.out.println(HORIZONTAL_RULE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + HORIZONTAL_RULE);

        return;
    }

    public static void exit() {
        System.out.println(HORIZONTAL_RULE
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_RULE);

        return;
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        greeting();
        Scanner sc = new Scanner(System.in);


        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("")) {
                System.out.println("Please enter something");
                continue;
            }

            if (userInput.equals("bye")) {
                break;
            }

            if(userInput.split(" ", 2).length <= 1){
                System.out.println("Invalid command structure. Use [command] [arguments[]]");
                System.out.println(HORIZONTAL_RULE);
                continue;
            }

            String keyword = userInput.split(" ", 2)[0];

            System.out.println(HORIZONTAL_RULE);

            if (keyword.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ". " + task.getListDescription());
                }

            } else if (keyword.equals("mark")) {
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                Task task = tasks.get(index);
                task.setIsDone(true);

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.getListDescription());

            } else if (keyword.equals("unmark")) {
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                Task task = tasks.get(index);
                task.setIsDone(false);

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.getListDescription());

            } else {
                String content = userInput.split(" ", 2)[1];

                if (keyword.equals("todo")) {
                    String description = content;

                    Task task = new Task(description, 'T');
                    tasks.add(task);

                } else if (keyword.equals("deadline")) {
                    String description = content.split(" /by ", 2)[0];
                    String by = content.split(" /by ", 2)[1];

                    Deadline deadline = new Deadline(description, 'D', by);
                    tasks.add(deadline);

                } else if (keyword.equals("event")) {
                    String description = content.split(" /from | /to ", 3)[0];
                    String from = content.split(" /from | /to ", 3)[1];
                    String to = content.split(" /from | /to ", 3)[2];

                    Event event = new Event(description, 'E', from, to);
                    tasks.add(event);

                } else {
                    // Error handling if keywords does not exist
                    System.out.println("Command not found, use [todo, deadline, event, mark, unmark, list]");
                    System.out.println(HORIZONTAL_RULE);
                    continue;
                }

                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size() - 1).getListDescription());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }

            System.out.println(HORIZONTAL_RULE);
        }

        exit();

    }
}
