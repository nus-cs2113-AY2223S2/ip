import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    final static String HORIZONTAL_LINE = "____________________________________________________________";

    public static void greeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE + '\n');

        return;
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE + '\n');

        return;
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();

        greeting();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            String firstWord = userInput.split(" ", 2)[0];

            System.out.println(HORIZONTAL_LINE);

            if (firstWord.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
                }

            } else if (firstWord.equals("mark")) {
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                Task task = tasks.get(index);
                task.setIsDone(true);

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());

            } else if (firstWord.equals("unmark")) {
                int index = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
                Task task = tasks.get(index);
                task.setIsDone(false);

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());

            } else {
                Task task = new Task(userInput, false);
                tasks.add(task);

                System.out.println("added: " + task.getDescription());
            }

            System.out.println(HORIZONTAL_LINE + '\n');
        }

        exit();

    }
}
