import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Greeting message
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Vivy");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (!line.equals("bye")) {
            String[] words = line.split(" ");
            if (line.equals("list")) {
                // List out all the tasks added
                System.out.println("\t____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t " + (i + 1) + "." + "[" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].getDescription());
                }
                System.out.println("\t____________________________________________________________");
            } else if (words[0].equals("unmark") && (words.length == 2)) {
                // Mark a task as not done
                int taskNumber = Integer.parseInt(words[1]);
                tasks[taskNumber - 1].markAsNotDone();
                // Printing out marked as not done message
                System.out.println("\t____________________________________________________________");
                System.out.println("Understood. I've marked this task as not done yet:");
                System.out.println("[" + tasks[taskNumber - 1].getStatusIcon() + "] " +
                        tasks[taskNumber - 1].getDescription());
                System.out.println("\t____________________________________________________________");
            } else if (words[0].equals("mark") && (words.length == 2)) {
                // Mark a task as done
                int taskNumber = Integer.parseInt(words[1]);
                tasks[taskNumber - 1].markAsDone();
                // Printing out marked as done message
                System.out.println("\t____________________________________________________________");
                System.out.println("Understood. I've marked this task as done:");
                System.out.println("[" + tasks[taskNumber - 1].getStatusIcon() + "] " +
                        tasks[taskNumber - 1].getDescription());
                System.out.println("\t____________________________________________________________");
            } else {
                // Adding a task to the list
                Task currentTask = new Task(line);
                tasks[taskCount] = currentTask;
                taskCount++;
                // Printing out added task message
                System.out.println("\t____________________________________________________________");
                System.out.println("\t added: " + currentTask.getDescription());
                System.out.println("\t____________________________________________________________");
            }
            line = in.nextLine();
        }

        // Exiting the program
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
