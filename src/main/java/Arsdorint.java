import java.util.Locale;
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
                "> Type \"bye\" to exit. \n" +
                        " What can I do for you? \n" +
                        "____________________________________________________________\n");
        while (true) {
            command = ask();
            if (command.toLowerCase().equals("bye")) break; //exit command
            else if (command.equalsIgnoreCase("list")) list();
            //add command when user don't type list or bye
            else add(command);
        }

        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
