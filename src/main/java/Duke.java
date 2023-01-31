import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] list = new Task[100];
        int nextEmptyIndex = 0;

        boolean done = false;

        while (!done) {
            line = in.nextLine();

            String[] input = line.split(" ", 2);
            String command = input[0];

            if (command.equals("bye")) {
                done = true;
            } else if (command.equals("list")) {
                handleShowTaskList(list, nextEmptyIndex);
            } else if (command.equals("mark")) {
                int indexToMark = Integer.parseInt(line.split(" ")[1]) - 1;
                handleMarkTask(list, indexToMark);
            } else if (command.equals("unmark")) {
                int indexToUnmark = Integer.parseInt(line.split(" ")[1]) - 1;
                handleUnmarkTask(list, indexToUnmark);
            } else if (command.equals("todo")) {
                String parameters = input[1];
                list[nextEmptyIndex] = new Task(parameters);
                nextEmptyIndex++;
                printAddedTask(list, nextEmptyIndex);
            } else if (command.equals("deadline")) {
                String[] deadlineParameters = input[1].split("/by");
                String description = deadlineParameters[0].trim();
                String deadline = deadlineParameters[1].trim();
                list[nextEmptyIndex] = new Deadline(description, deadline);
                nextEmptyIndex++;
                printAddedTask(list, nextEmptyIndex);
            } else if (command.equals("event")) {
                String description = input[1].split("/from")[0].trim();
                String from = input[1].split("/from")[1].split("/to")[0].trim();
                String to = input[1].split("/from")[1].split("/to")[1].trim();
                list[nextEmptyIndex] = new Event(description, from, to);
                nextEmptyIndex++;
                printAddedTask(list, nextEmptyIndex);
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Sorry, I don't know what that means...");
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void handleShowTaskList(Task[] list, int nextEmptyIndex) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < nextEmptyIndex; i++) {
            String prefix = (i + 1) + ".";
            System.out.println(prefix + list[i].getLabel());
        }
        System.out.println("____________________________________________________________");
    }

    public static void handleMarkTask(Task[] list, int indexToMark) {
        list[indexToMark].setIsDone(true);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list[indexToMark].getLabel());
        System.out.println("____________________________________________________________");
    }

    public static void handleUnmarkTask(Task[] list, int indexToUnmark) {
        list[indexToUnmark].setIsDone(false);
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list[indexToUnmark].getLabel());
        System.out.println("____________________________________________________________");
    }

    private static void printAddedTask(Task[] list, int nextEmptyIndex) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(list[nextEmptyIndex-1].getLabel());
        System.out.println("Now you have " + nextEmptyIndex + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
