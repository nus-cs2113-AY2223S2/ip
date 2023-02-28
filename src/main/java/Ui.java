import java.util.ArrayList;

public class Ui {
    protected static final String line = "__________________________________________________________";

    private static TaskList taskList;
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, INDEXOUT
    }
    public static void printMessage(Task t, CommandType c) {
        System.out.println(line);
        switch(c) {
        case TODO:
            System.out.println("Gotcha, this task has been added to the list: ");
            System.out.println(t.toString());
            System.out.println("There are " + (TaskList.getNumTasks()+1) + " tasks in your list :)");
            break;
        case DEADLINE:
            System.out.println("Gotcha, this deadline has been added to the list: ");
            System.out.println(t.toString());
            System.out.println("There are " + (TaskList.getNumTasks()+1) + " tasks in your list :)");
            break;
        case EVENT:
            System.out.println("Gotcha, this event has been added to the list: ");
            System.out.println(t.toString());
            System.out.println("There are " + (TaskList.getNumTasks()+1) + " tasks in your list :)");
            break;
        case DELETE:
            System.out.println("Okay, I have deleted this task: ");
            System.out.println(t.toString());
            System.out.println("There are " + (TaskList.getNumTasks()) + " tasks in your list :)");
            break;
        case MARK:
            System.out.println("Gotcha, this task has been marked done: ");
            System.out.println(t.toString());
            break;
        case UNMARK:
            System.out.println("Gotcha, this task has been unmarked. Remember to complete it: ");
            System.out.println(t.toString());
            break;
        }
        System.out.println(line);
    }

    public static void printMessage(CommandType c) {
        System.out.println(line);
        switch(c) {
        case LIST:
            if (taskList.getTasks().isEmpty()) {
                System.out.println("Your task list is empty. Time to add some tasks!");
            } else {
                System.out.println("Here are the tasks in your list: ");
                printList(taskList.getTasks());
            }
            break;
        case INDEXOUT:
            System.out.println("Oops.. I don't know what this means :(");
        }
        System.out.println(line);
    }

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

    public static void printList(ArrayList<Task> tasks) {
        tasks.trimToSize();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            } else {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
    }

    public static void printBye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line + '\n');
        return;
    }
}
