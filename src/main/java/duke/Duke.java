package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String DIVIDER_LINE = "______________________________\n";
    protected static boolean shouldContinue = true;
    protected static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = DIVIDER_LINE
                + "Hello! I'm Duke\n"
                + "What can i do for you\n"
                + DIVIDER_LINE;
        System.out.println(greet);
        Scanner in = new Scanner(System.in);
        String action;
        ArrayList<Task> tasks = new ArrayList<>();
        while (shouldContinue) {
            action = in.nextLine();
            try {
                tasks = handleAction(tasks, action);
            } catch (DukeException e) {
                System.out.println(DIVIDER_LINE + "Sorry! I don't know what that means.\n" + DIVIDER_LINE);
            } catch (DukeException.TaskEmpty e) {
                System.out.println(DIVIDER_LINE + "The content of the task cannot be empty!\n" + DIVIDER_LINE);
            }
        }
    }

    public static ArrayList<Task> handleAction(ArrayList<Task> tasks, String action) throws DukeException, DukeException.TaskEmpty{
        if (action.equals("bye")) {
            System.out.println(DIVIDER_LINE + "Bye. Hope to see you again soon!\n" + DIVIDER_LINE);
            shouldContinue = false;
        } else if (action.equals("list")) {
            System.out.print(DIVIDER_LINE);
            for (int i = 0; i < taskCount; i += 1) {
                System.out.println(Integer.toString(i + 1) + ". " +tasks.get(i).toString());
            }
            System.out.println(DIVIDER_LINE);
        } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
            throw new DukeException.TaskEmpty();
        } else if (action.startsWith("mark")){
            int dividerPos = action.indexOf(" ");
            int toBeMarked = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
            tasks.get(toBeMarked).markAsDone();
            printMarked(tasks.get(toBeMarked), action.split(" ")[0]);
        } else if (action.startsWith("unmark")) {
            int dividerPos = action.indexOf(" ");
            int toBeUnmarked = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
            tasks.get(toBeUnmarked).markAsUndone();
            printMarked(tasks.get(toBeUnmarked), action.split(" ")[0]);
        } else if (action.startsWith("delete")) {
            int dividerPos = action.indexOf(" ");
            int toBeDeleted = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
            Task removedTask = tasks.get(toBeDeleted);
            tasks.remove(toBeDeleted);
            taskCount -= 1;
            printDeleted(removedTask);
            printNumTask();
        } else if (action.startsWith("todo")){
            Task tempTask = new Task(action.substring(5));
            tasks.add(tempTask);
            printAdded(tasks);
            taskCount += 1;
            printNumTask();
        } else if (action.startsWith("deadline")) {
            int dividerPosition = action.indexOf("/by");
            Task tempTask = new Deadline(action.substring(9,dividerPosition - 1),
                    action.substring(dividerPosition + 4));
            tasks.add(tempTask);
            printAdded(tasks);
            taskCount += 1;
            printNumTask();
        } else if (action.startsWith("event")) {
            int dividerPosition1 = action.indexOf("/from");
            int dividerPosition2 = action.indexOf("/to");
            //extract the event details
            Task tempTask = new Event(action.substring(6,dividerPosition1 - 1),
                    action.substring(dividerPosition1 + 6, dividerPosition2 - 1),
                    action.substring(dividerPosition2 + 4));
            tasks.add(tempTask);
            printAdded(tasks);
            taskCount += 1;
            printNumTask();
        } else {
            throw new DukeException();
        }
        return tasks;
    }

    private static void printMarked(Task tasks, String action) {
        System.out.println(DIVIDER_LINE + "Nice! I've marked this task as "+ (action.equals("mark") ? "done:" : "undone:")
                + "\n" + tasks.toString() + "\n" + DIVIDER_LINE);
    }

    private static void printAdded(ArrayList<Task> tasks) {
        System.out.println(DIVIDER_LINE + "added:\n" + tasks.get(taskCount).toString() + "\n" + DIVIDER_LINE);
    }

    private static void printDeleted(Task deletedTask) {
        System.out.println(DIVIDER_LINE + "deleted:\n" + deletedTask.toString() + "\n" + DIVIDER_LINE);
    }

    public static void printNumTask() {
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }
}
