import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }

    private static String[] list = new String[100];
    private static int listCount = 0;


    public static void greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
                System.out.println(greeting);
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public static void addTask(String userInput) {
        list[listCount] = userInput;
        listCount ++;
    }

    public static void echo() {
        for (int x = 0; x < 100; x += 1) {
            String command = getUserInput();
            System.out.println("____________________________________________________________");
            if (command.compareTo("bye") == 0) {
                System.out.println("Bye. Hope to see you again soon!\n" + "____________________________________________________________");
                break;
            } else if (command.compareTo("list") == 0) {
                for (int y = 0; y < listCount; y += 1) {
                    int taskN = y + 1;
                    System.out.println(taskN + ". " + list[y]);
                }
                System.out.println("____________________________________________________________");
            } else {
                addTask(command);
                System.out.println("added: " + command + "\n" + "____________________________________________________________");
            }
        }
    }
}
