package Duke;

import Duke.commands.Task;

import java.util.ArrayList;

public class Ui {
    protected static final String dividingLine = "\n————————————————————————————————————————————————————————\n";
    protected static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * The method is to print Greetings from Duke on the Screen
     */
    public static void printHello() {
        System.out.println(dividingLine);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?" + dividingLine);
    }

    /**
     * The method is to print farewell from Duke on the screen
     */
    public static void printBye(){
        System.out.println(dividingLine + "Bye. Hope to see you again soon!" + dividingLine);
    }

    /**
     * The method prints the task marked by the user on the screen.
     *
     * @param task the task marked
     */
    public static void printMark(String task) {
        System.out.println(dividingLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println(dividingLine);
    }

    /**
     * The method prints the task unmarked by the user on the screen.
     *
     * @param task the task unmarked
     */
    public static void printUnmark(String task) {
        System.out.println(dividingLine);
        System.out.println("Noted. I've unmarked this task:");
        System.out.println(task);
        System.out.println(dividingLine);
    }

    public static void printDelete(String task, int size) {
        System.out.println(dividingLine + System.lineSeparator() + "Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + (size - 1) + " tasks in the list." + System.lineSeparator() + dividingLine);
    }

    public static void printAddTask(String description, int size) {
        System.out.println(dividingLine+ System.lineSeparator() + "Got it. I've added this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in the list." + System.lineSeparator() + dividingLine);
    }

    /**
     * The method lists all the tasks in the task list
     *
     * @param tasks the task list
     */
    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println(dividingLine);
            System.out.println("No task found");
            System.out.println(dividingLine);
            return;
        }
        System.out.println(dividingLine + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + (tasks.get(i)).toString());
        }
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + dividingLine);
    }

    /**
     * The method executes when unrecognised command is input by the user
     */
    public static void printUnrecognisableCommand(){
        System.out.println(dividingLine + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                + dividingLine);
    }
}
