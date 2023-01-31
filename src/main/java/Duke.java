import java.util.Scanner;

public class Duke {

    static final int MAX_COMMANDS = 100;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        generateOutput();
    }

    private static Task[] taskList = new Task[MAX_COMMANDS];
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
        taskList[listCount] = new Task(userInput);
        listCount ++;
        System.out.println("added: " + userInput + "\n" + "____________________________________________________________");
    }

    public static void farewell() {
        System.out.println("Bye. Hope to see you again soon!\n" + "____________________________________________________________");
    }

    public static void printList() {
        for (int y = 0; y < listCount; y += 1) {
            int taskN = y + 1;
            System.out.println(taskN + ".[" + taskList[y].status + "] " + taskList[y].taskName);
        }
        System.out.println("____________________________________________________________");
    }

    public static void generateOutput() {
        for (int x = 0; x < MAX_COMMANDS; x += 1) {
            String command = getUserInput();
            System.out.println("____________________________________________________________");
            if (command.compareTo("bye") == 0) {
                farewell();
                break;
            } else if (command.compareTo("list") == 0) {
                printList();
            } else if (command.contains("unmark ") == true) {
                String taskN = command.substring(7);
                int taskNum = Integer.parseInt(taskN);
                taskList[taskNum - 1].setStatusChar(" ");
                System.out.println(taskNum + ".[" + taskList[taskNum - 1].status + "] " + taskList[taskNum - 1].taskName + "\n____________________________________________________________");
            } else if (command.contains("mark ") == true) {
                String taskN = command.substring(5);
                int taskNum = Integer.parseInt(taskN);
                taskList[taskNum - 1].setStatusChar("X");
                System.out.println(taskNum + ".[" + taskList[taskNum - 1].status + "] " + taskList[taskNum -1].taskName + "\n____________________________________________________________");
            } else {
                addTask(command);
            }
        }
    }
}
