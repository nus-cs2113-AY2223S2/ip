import java.util.Scanner;

public class Duke {

    /**
     * Returns boolean value of true if input String is an integer,
     * else returns boolean value of false
     *
     * @param word
     * @return true if input String is an integer, otherwise false
     */
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
        greetingMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (!line.equals("bye")) { // Exits the program if input is "bye"
            String[] words = line.split(" ");
            if (line.equals("list")) {
                // List out all the tasks added
                list(tasks, taskCount);
            } else if (words[0].equals("unmark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as not done
                unmarkTask(tasks, taskCount, words);
            } else if (words[0].equals("mark") && (words.length == 2) && (isNumeric(words[1]))) {
                // Mark a task as done
                markTask(tasks, taskCount, words);
            } else {
                // Adding a task to the list
                addTask(line, tasks, taskCount);

                // Printing out added task message
                addedTaskMessage(tasks[taskCount], taskCount);
                taskCount++;
            }
            line = in.nextLine();
        }

        // Exiting the program
        exitMessage();
    }

    private static void addTask(String line, Task[] tasks, int taskCount) {
        if (line.contains(" /by")) {
            // Adding a Deadline
            String description = line.substring(0, line.indexOf("/by")).trim();
            String deadline = line.substring(line.indexOf("/by") + 3).trim();
            tasks[taskCount] = new Deadline(description, deadline);
        } else if (line.contains(" /from") && line.contains(" /to")) {
            // Adding an Event
            String description = line.substring(0, line.indexOf("/from")).trim();
            String start = line.substring(line.indexOf("/from") + 5, line.indexOf("/to")).trim();
            String end = line.substring(line.indexOf("/to") + 3).trim();
            tasks[taskCount] = new Event(description, start, end);
        } else {
            // Adding a _Todo_
            tasks[taskCount] = new Todo(line);
        }
    }

    private static void borderLine() {
        System.out.println("\t____________________________________________________________");
    }

    private static void exitMessage() {
        borderLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        borderLine();
    }

    private static void addedTaskMessage(Task currentTask, int taskCount) {
        borderLine();
        System.out.println("\t Alright, I have added this task: \n\t\t" + currentTask);
        System.out.println("\t You now have " + (taskCount + 1) + " tasks in your list.");
        borderLine();
    }

    private static void markTask(Task[] tasks, int taskCount, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            borderLine();
            System.out.println("\t Task " + taskNumber + " does not exist.");
            borderLine();
        } else {
            tasks[taskNumber - 1].markAsDone();
            // Printing out marked as done message
            borderLine();
            System.out.println("\t Understood. I've marked this task as done:");
            System.out.println("\t " + tasks[taskNumber - 1]);
            borderLine();
        }
    }

    private static void unmarkTask(Task[] tasks, int taskCount, String[] words) {
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > taskCount) {
            // Input task number exceeds the number of tasks in the list
            borderLine();
            System.out.println("\t Task " + taskNumber + " does not exist.");
            borderLine();
        } else {
            tasks[taskNumber - 1].markAsNotDone();
            // Printing out marked as not done message
            borderLine();
            System.out.println("\t Understood. I've marked this task as not done yet:");
            System.out.println("\t " + tasks[taskNumber - 1]);
            borderLine();
        }
    }

    private static void list(Task[] tasks, int taskCount) {
        borderLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks[i]);
        }
        borderLine();
    }

    private static void greetingMessage() {
        borderLine();
        System.out.println("\t Hello! I'm Vivy.");
        System.out.println("\t Here are some commands you can give me:");
        System.out.println("\t - list: I'll list out all the tasks you have recorded.");
        System.out.println("\t - mark <task_number>: I'll mark that task as done.");
        System.out.println("\t - unmark <task_number>: I'll mark that task as undone.");
        System.out.println("\t - bye: I will shut down my program.");
        System.out.println("\t - Anything else will be recorded as a task. \n");
        System.out.println("\t Format for tasks:");
        System.out.println("\t   Deadlines: <description> /by <deadline>");
        System.out.println("\t              (eg. Eat bread /by Thursday)");
        System.out.println("\t      Events: <description> /from <start date/time> /to <end date/time>");
        System.out.println("\t              (eg. Meeting /from March 3 8pm /to 9pm)");
        System.out.println("\t        Todo: <description>");
        System.out.println("\t              (eg. Water the plants)");
        System.out.println("\t Wrong formats for Deadlines and Events will default to a Todo task. \n");
        System.out.println("\t What can I do for you?");
        borderLine();
    }
}
