import java.util.Scanner;
public class Arsdorint {
    static Scanner input = new Scanner(System.in);
    static String logo = "    ___                         _                                 _\n"
            + "   / _ \\     _____   _____  ___| |   ___    _____   _   _____   _| |_\n"
            + "  / /_\\ \\   /  ___| /  __/ /  _  |  / _ \\  /  ___| | | |  _  \\ |_   _|\n"
            + " / _____ \\  | /    __\\ \\   | |_| | | |_| | | /     | | | | | |   | |\n"
            + "/_/     \\_\\ |_|   /____/   \\_____|  \\___/  |_|     |_| |_| |_|   |_|\n";

    static Task[] toDoList = new Task[100];
    public static void echo(String command) {
        System.out.println(command);
    }

    public static String ask() {
        String ask;
        return ask = input.nextLine();
    }

    public static void list() {
        for (int i = 0; i < Task.numOfTasks; i++) {
            System.out.print(i + 1);
            System.out.print(". " + toDoList[i].getStatus() + " ");
            System.out.println(toDoList[i].description);
        }
    }

    public static void add(String command) {
        toDoList[Task.numOfTasks] = new Task(command);
        System.out.print("Added: ");
        echo(command);
    }

    public static void mark(String command) {
        String[] num = command.split(" ");
        int idx = Integer.parseInt(num[1]);
        if (num[0].equalsIgnoreCase("mark"))
            toDoList[idx - 1].isDone = true;
        else toDoList[idx - 1].isDone = false;
        list();
    }

    public static void unmark(String command) {
        String[] num = command.split(" ");
        int idx = Integer.parseInt(num[1]);
        if (num[0].equalsIgnoreCase("mark"))
            toDoList[idx - 1].isDone = false;
        else toDoList[idx - 1].isDone = true;
        list();
    }

    public static void main(String[] args) {
        //System.out.println("____________________________________________________________\n" +
                //" Hello! I'm Arsdorint\n" +
                //" What can I do for you?");
        String command = " ";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Arsdorint, a member of Arsdorint Team.\n" +
                " Please Type The Command As Follow:\n" +
                "> Type anything to add to the to-do list. \n" +
                "> Type \"list\" to list all the tasks. \n" +
                "> Type \"mark\" follow by a number x to mark x tasks in the list" +
                "> Type \"unmark\" follow by a number y to unmark y tasks in the list" +
                "> Type \"bye\" to exit. \n" +
                        " What can I do for you? \n" +
                        "____________________________________________________________\n");
        while (true) {
            command = ask();
            if (command.toLowerCase().equals("bye")) break; //exit command
            else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                list();
            }
            else if (command.toLowerCase().contains("mark")) {
                System.out.println("Nice! I've marked this task as done: ");
                mark(command);
            }
            else if (command.toLowerCase().contains("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                unmark(command);
            }
            //add command when user don't type the instruction's command
            else add(command);
        }

        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
