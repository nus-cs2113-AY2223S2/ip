import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static void printDivider() {
        System.out.println("\t____________________________________________________________");
    }

    private static void addTask(String taskContent) {
        Task task = new Task(taskContent);
        taskList.add(task);
        System.out.println("\t added: " + task.getContent());
    }

    private static void markTask(int listID) {
        int index = listID-1;
        if (index < 0 || index >= taskList.size()) {
            System.err.println("\t Index out of range!");
        } else {
            taskList.get(index).mark();
        }
    }

    private static void unmarkTask(int listID) {
        int index = listID-1;
        if (index < 0 || index >= taskList.size()) {
            System.err.println("\t Index out of range!");
        } else {
            taskList.get(index).unmark();
        }
    }

    private static void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        int cnt = 0;
        for (Task task : taskList) {
            System.out.printf("\t %d.%s\n", ++cnt, task);
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
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        printDivider();

        // user interaction: echo
        while (true) {
            String msg = scanner.nextLine();
            String[] msgArgs = msg.split(" ");
            printDivider();

            if (msg.equals("bye")) {
                break;
            } else if (msg.equals("list")) {
                listTasks();
            } else if (msgArgs[0].equals("mark")) {
                markTask(Integer.parseInt(msgArgs[1]));
            } else if (msgArgs[0].equals("unmark")) {
                unmarkTask(Integer.parseInt(msgArgs[1]));
            } else {
                addTask(msg);
            }
            printDivider();
        }

        // exit
        System.out.println("\t Bye. Hope to see you again soon!");
        printDivider();
    }
}
