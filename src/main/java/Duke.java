import java.util.Scanner;

public class Duke {
    private static Task[] taskItems = new Task[100];
    private static int taskItemCount = 0;

    public static void addTaskItems(String line) {
        taskItems[taskItemCount] = new Task(line);
        taskItemCount++;
        String printAddItem = "════════════════════════════════════════════════════════════\n"
                + " added: "
                + line
                + "\n"
                + "════════════════════════════════════════════════════════════\n\n";
        System.out.print(printAddItem);
    }

    public static void markTaskItems(int taskItemNo, String line) {
        if (line.startsWith("mark")) {
            taskItems[taskItemNo].setCompleted();
            String printTaskCompleted = "════════════════════════════════════════════════════════════\n"
                    + "Good Job! I've marked this task as completed:\n ["
                    + taskItems[taskItemNo].getTaskStatus()
                    + "] "
                    + taskItems[taskItemNo].getTaskName()
                    + "\n ════════════════════════════════════════════════════════════\n\n";
            System.out.print(printTaskCompleted);
        } else {
            taskItems[taskItemNo].setIncomplete();;
            String printTaskIncomplete = "════════════════════════════════════════════════════════════\n"
                    + "Noted, I have marked this task as incomplete:\n ["
                    + taskItems[taskItemNo].getTaskStatus()
                    + "] "
                    + taskItems[taskItemNo].getTaskName()
                    + "\n ════════════════════════════════════════════════════════════\n\n";
            System.out.print(printTaskIncomplete);
        }
    }

    public static void printTaskItems () {
        System.out.print("════════════════════════════════════════════════════════════\n"
                + " Here are the tasks in your list:\n");

        for (int i = 0; i < taskItemCount; i++) {
            String printItem = " " + (i + 1) + ".[" + taskItems[i].getTaskStatus() + "] "
                    + taskItems[i].getTaskName() + "\n";
            System.out.print(printItem);
        }

        System.out.print("════════════════════════════════════════════════════════════\n\n");
    }

    public static void main(String[] args) {
        String greet = "════════════════════════════════════════════════════════════\n"
                + " Hello! I'm Chatty\n"
                + " How can I help you?\n"
                + "════════════════════════════════════════════════════════════\n\n";
        System.out.print(greet);

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if (line.equals("list")) {
                printTaskItems();
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                int taskItemNo = Integer.parseInt(line.substring(line.length() - 1)) - 1;
                markTaskItems(taskItemNo, line);
            } else if (line.equals("bye")) {
                String printExit = "════════════════════════════════════════════════════════════\n"
                        + " Goodbye. Hope to see you again soon!\n"
                        + "════════════════════════════════════════════════════════════\n";
                System.out.print(printExit);
                break;
            } else {
                addTaskItems(line);
            }
        }
    }
}