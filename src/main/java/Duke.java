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

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static boolean parseUserInput(String input) {
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";
        switch (command) {
        case "bye":
            return false;
        case "list":
            taskList.listTasks();
        case "help":
            help();
        case "mark":
            taskList.markDone(Integer.parseInt(args));
        case "unmark":
            taskList.unmarkDone(Integer.parseInt(args));
        default:
            taskList.addTask(command);
        }
        return true;
    }

    public static TaskList taskList = new TaskList(100);
    public static void main(String[] args) {
        greetUser();
        // echo();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (parseUserInput(line)) {
            printLine();
            line = scanner.nextLine();
        }
        scanner.close();
        exit();
    }
}
