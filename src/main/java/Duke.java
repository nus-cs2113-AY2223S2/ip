import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int currentIndex = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            String input;
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (Task task : list) {
                    if (task != null) {
                        System.out.println(task.getIndex() + ".[" + task.getStatusIcon() + "] " + task.getDescription());
                    }
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark")) {
                String[] temp = input.split(" ", 2);
                int taskIndex = Integer.valueOf(temp[1]);
                Task curTask = list[taskIndex - 1];
                curTask.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + curTask.getStatusIcon() + "] " + curTask.getDescription());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark")) {
                String[] temp = input.split(" ", 2);
                int taskIndex = Integer.valueOf(temp[1]);
                Task curTask = list[taskIndex - 1];
                curTask.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + curTask.getStatusIcon() + "] " + curTask.getDescription());
                System.out.println("____________________________________________________________");
            } else {
                Task task = new Task(currentIndex + 1, input);
                list[currentIndex] = task;
                currentIndex++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
    }
}

