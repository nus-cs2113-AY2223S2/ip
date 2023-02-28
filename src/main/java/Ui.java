import java.util.ArrayList;

public class Ui {
    protected static final String line = "__________________________________________________________";
    private static int numTasks = 0;

    public static void greet() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + '\n' + logo);
        System.out.println("Hello! i'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void printList(ArrayList<Task> input) {
        System.out.println(line + "\nHere are the tasks in your list: ");
        input.trimToSize();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) == null) {
                break;
            } else {
                System.out.println((i+1) + ". " + input.get(i));
            }
        }
        System.out.println(line);
    }

    public static void printAddTask(Task t) {
        //print to show Task added to list
        System.out.println(line);
        System.out.println("Got it. I've added this task: \n" + t);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.println(line);
    }


    public static void printBye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line + '\n');
        return;
    }
}
