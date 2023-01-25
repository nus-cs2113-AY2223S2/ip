import java.util.Scanner;

public class Duke {

    // Returns true if input string is an integer, otherwise returns false
    public static boolean isNumeric(String word) {
        int valueToConvert;
        try {
            valueToConvert = Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input, please enter an integer.");
        }
        return false;
    }

    public static void main(String[] args) {
        // Greeting message
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Vivy.");
        System.out.println("\t Here are some commands you can give me:");
        System.out.println("\t - list: I'll list out all the tasks you have recorded.");
        System.out.println("\t - mark <task_number>: I'll mark that task as done.");
        System.out.println("\t - unmark <task_number>: I'll mark that task as undone.");
        System.out.println("\t - bye: I will shut down my program.");
        System.out.println("\t - Anything else will be recorded as a task. \n");
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
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t " + (i + 1) + "." + "[" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].getDescription());
                }
                System.out.println("\t____________________________________________________________");
            } else if (words[0].equals("unmark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as not done
                int taskNumber = Integer.parseInt(words[1]);
                if (taskNumber > taskCount) {
                    // Input task number exceeds the number of tasks in the list
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Task " + taskNumber + " does not exist.");
                    System.out.println("\t____________________________________________________________");
                } else {
                    tasks[taskNumber - 1].markAsNotDone();
                    // Printing out marked as not done message
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Understood. I've marked this task as not done yet:");
                    System.out.println("\t [" + tasks[taskNumber - 1].getStatusIcon() + "] " +
                            tasks[taskNumber - 1].getDescription());
                    System.out.println("\t____________________________________________________________");
                }
            } else if (words[0].equals("mark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as done
                int taskNumber = Integer.parseInt(words[1]);
                if (taskNumber > taskCount) {
                    // Input task number exceeds the number of tasks in the list
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Task " + taskNumber + " does not exist.");
                    System.out.println("\t____________________________________________________________");
                } else {
                    tasks[taskNumber - 1].markAsDone();
                    // Printing out marked as done message
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Understood. I've marked this task as done:");
                    System.out.println("\t [" + tasks[taskNumber - 1].getStatusIcon() + "] " +
                            tasks[taskNumber - 1].getDescription());
                    System.out.println("\t____________________________________________________________");
                }
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
