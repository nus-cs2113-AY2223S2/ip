import java.util.Scanner;

public class Duke {
    private static boolean hasEnteredBye = false;

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
        System.out.println("todo <name>: Adds a ToDo task.");
        System.out.println("deadline <name> /by <by when>: Adds a deadline task" + 
                " with specified deadline.");
        System.out.println("event <name> /from <from when> /to <to when>:" + 
                " Adds an event with specified time period.");
        System.out.println("mark <task number>: Marks the task specified as done.");
        System.out.println("unmark <task number>: Marks the task specified as not done.");
        System.out.println("bye: Exits the program.");
    }

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void parseUserInput(String input) {
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";
        switch (command) {
            case "bye":
                hasEnteredBye = true;
                exit();
                break;
            case "list":
                taskList.listTasks();
                break;
            case "help":
                help();
                break;
            case "mark":
                try {
                    taskList.markDone(Integer.parseInt(args));
                } catch (Exception e) {
                    System.out.println("Please input an integer after mark.");
                }
                break;
            case "unmark":
                try {
                    taskList.unmarkDone(Integer.parseInt(args));
                } catch (Exception e) {
                    System.out.println("Please input an integer after unmark.");
                }
                break;
            case "todo":
                taskList.addToDo(args);
                break;
            case "deadline":
                taskList.addDeadline(args);
                break;
            case "event":
                taskList.addEvent(args);
                break;
            default:
                System.out.println("Invalid command entered, please enter 'help' to see " +
                        "the list of commands.");
        }
    }

    public static TaskList taskList = new TaskList(100);

    public static void main(String[] args) {
        greetUser();

        Scanner scanner = new Scanner(System.in);
        while (!hasEnteredBye) {
            String line = scanner.nextLine();
            parseUserInput(line);
            printLine();
        }
        scanner.close();
    }
}
