import java.util.Scanner;

public class Duke {
    public static void greetUser() {
        printLine();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void help() {
        System.out.println("List of commands:");
        System.out.println("list: Lists all tasks.");
        System.out.println("mark <task number>: Marks the task specified as done.");
        System.out.println("unmark <task number>: Marks the task specified as not done.");
        System.out.println("bye: Exits the program.");
        System.out.println("Anything entered that is not a command will be automatically added to "
                + "the list of tasks.");
    }

    /*
     * public static void echo() {
     * Scanner scanner = new Scanner(System.in);
     * while (true) {
     * String line = scanner.nextLine();
     * System.out.println(
     * "____________________________________________________________");
     * System.out.println(line);
     * System.out.println(
     * "____________________________________________________________");
     * if (line.equals("bye")) {
     * scanner.close();
     * break;
     * }
     * }
     * }
     */

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        greetUser();
        // echo();
        TaskList taskList = new TaskList(100);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                taskList.listTasks();
            } else if (line.equals("help")) {
                help();
            } else if (line.length() >= 4 && line.substring(0, 4).equals("mark")) {
                taskList.markDone(Integer.parseInt(line.substring(5)));
            } else if (line.length() >= 6 && line.substring(0, 6).equals("unmark")) {
                taskList.unmarkDone(Integer.parseInt(line.substring(7)));
            } else {
                taskList.addTask(line);
            }
            printLine();
            line = scanner.nextLine();
        }
        scanner.close();
        exit();
    }
}
