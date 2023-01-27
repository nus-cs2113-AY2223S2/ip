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
    public static void printAddedTask(Task addedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + addedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
    public static void printMarkedTask(int taskNumber, ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + taskList.get(taskNumber));
        System.out.println("    ____________________________________________________________");
    }
    public static void printUnmarkedTask(int taskNumber, ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + taskList.get(taskNumber));
        System.out.println("    ____________________________________________________________");
    }
    public static void printList(ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i+1) + "." + taskList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void addTodoTask(String description, ArrayList<Task> taskList) {
        Todo newTodoTask = new Todo(description);
        taskList.add(newTodoTask);
        printAddedTask(newTodoTask, taskList.size());
    }

    public static void addDeadlineTask(String description, ArrayList<Task> taskList) {
        int firstSlashIndex = description.indexOf("/");
        String details = description.substring(0, firstSlashIndex - 1);
        String by = description.substring(firstSlashIndex + 4);
        Deadline newDeadlineTask = new Deadline(details, by);
        taskList.add(newDeadlineTask);
        printAddedTask(newDeadlineTask, taskList.size());
    }

    public static void addEventTask(String description, ArrayList<Task> taskList) {
        int firstSlashIndex = description.indexOf("/");
        int secondSlashIndex = description.indexOf("/", firstSlashIndex + 1);
        String details = description.substring(0, firstSlashIndex - 1);
        String from = description.substring(firstSlashIndex + 6, secondSlashIndex - 1);
        String to = description.substring(secondSlashIndex + 4);
        Event newEventTask = new Event(details, from, to);
        taskList.add(newEventTask);
        printAddedTask(newEventTask, taskList.size());
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
        ArrayList<Task> taskList = new ArrayList<>();
        while (input.hasNextLine()) {
            String[] nextInput = input.nextLine().split(" ", 2);
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
            if (nextInput[0].equals("todo")) {
                addTodoTask(nextInput[1], taskList);
            }
            if (nextInput[0].equals("deadline")) {
                addDeadlineTask(nextInput[1], taskList);
            }
            if (nextInput[0].equals("event")) {
                addEventTask(nextInput[1], taskList);
            }

        }
        endDuke();
    }

}


