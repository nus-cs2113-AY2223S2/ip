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
                ;
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
