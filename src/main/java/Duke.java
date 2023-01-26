import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> taskList = new ArrayList<>();

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static void addTask(String task) {
        taskList.add(task);
        System.out.println("\tadd: " + task);
    }

    private static void listTasks() {
        int cnt = 0;
        for (String task : taskList) {
            System.out.printf("\t%d. %s\n", ++cnt, task);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDivider();

        // user interaction: echo
        while (true) {
            String msg = scanner.nextLine();
            if (msg.equals("bye")) {
                break;
            } else if (msg.equals("list")) {
                printDivider();
                listTasks();
                printDivider();
            } else {
                printDivider();
                addTask(msg);
                printDivider();
            }
        }

        // exit
        printDivider();
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider();
    }
}
