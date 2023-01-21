import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void startDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void endDuke() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void printAddedTask(String addedTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + addedTask);
        System.out.println("    ____________________________________________________________");
    }

    public static void printMarkedTask(int taskNumber, ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDetails());
        System.out.println("    ____________________________________________________________");
    }

    public static void printUnmarkedTask(int taskNumber, ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       [" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDetails());
        System.out.println("    ____________________________________________________________");
    }

    public static void printList(ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i+1) + ".[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDetails());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startDuke();
        Scanner input = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (input.hasNextLine()) {
            String[] nextInput = input.nextLine().split(" ");
            if (nextInput[0].equals("bye")) {
                break;
            }
            if (nextInput[0].isEmpty()) { //settle for the case of empty inputs
                continue;
            }
            if (nextInput[0].equals("list")) { //want to print out the task list
                printList(taskList);
                continue;
            }
            if (nextInput[0].equals("mark")) {
                int taskNumber = Integer.parseInt(nextInput[1]);
                taskList.get(taskNumber - 1).markAsDone();
                printMarkedTask(taskNumber - 1, taskList);
                continue;
            }
            if (nextInput[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(nextInput[1]);
                taskList.get(taskNumber - 1).markAsNotDone();
                printUnmarkedTask(taskNumber - 1, taskList);
                continue;
            }
            Task newTask = new Task(String.join(" ", nextInput));
            taskList.add(newTask);
            printAddedTask(newTask.getDetails());
        }
        endDuke();
    }

}
