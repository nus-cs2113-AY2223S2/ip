import java.util.Scanner;

public class Duke {

    private static Tasks[] list_of_tasks = new Tasks[101];
    private static int counter = 0;

    public static void greet_user() {
        System.out.println("Hello! I'm Duke \n");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("How can i help u? \n");
    }

    public static void print_action() {
        System.out.println("\n");
        System.out.println("I have added this task: ");
        System.out.println(list_of_tasks[counter - 1].toString());

        System.out.println("You now have " + counter + " tasks in the list.");
    }

    public static void Todo(String description) {
        Tasks t = new ToDo(description);
        list_of_tasks[counter] = t;
        counter++;
        print_action();
    }

    public static void Deadline(String description, String by) {
        Deadline d = new Deadline(description, by);
        list_of_tasks[counter] = d;
        counter++;
        print_action();
    }

    public static void Event(String description, String start, String end) {
        Event E = new Event(description, start, end);
        list_of_tasks[counter] = E;
        counter++;
        print_action();
    }

    public static void List() {
        System.out.println("Here are all of your " + counter + " tasks: ");
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + "." + list_of_tasks[i].toString());
        }
    }

    public static void main(String[] args) {
        greet_user();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] command = input.split(" ", 2);

        while (!"bye".equals(input)) {
            switch (command[0]) {
                case "todo":
                    try {
                        Todo(command[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "deadline":
                    try {
                        String[] d = command[1].split("/by", 2);
                        String d_description = d[0];
                        String d_by = d[1];
                        Deadline(d_description, d_by);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "event":
                    try {
                        String[] e = command[1].split("/", 3);
                        String e_description = e[0];
                        String e_start = e[1];
                        String e_end = e[2];
                        Event(e_description, e_start, e_end);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "list":
                    List();
                    break;
                case "mark":
                    try {
                        Integer m_index = Integer.valueOf(command[1]);
                        list_of_tasks[m_index - 1].markAsDone();
                        System.out.println("Nice! This task is completed");
                        System.out.println(list_of_tasks[m_index - 1].toString());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                case "unmark":
                    try {
                        Integer u_index = Integer.valueOf(command[1]);
                        list_of_tasks[u_index - 1].markAsUnDone();
                        System.out.println("Nice! This task is completed");
                        System.out.println(list_of_tasks[u_index - 1].toString());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(":( There is an error (Index is out of bounds/negative)");
                    }
                    break;
                default:
                    System.out.println("There was an error. Please try again");
                    break;
            }
            input = scan.nextLine();
            command = input.split(" ", 2);
        }
        System.out.println("Goodbye. Hope to see u again :) \n");
        scan.close();
    }

}