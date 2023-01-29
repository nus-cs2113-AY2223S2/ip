import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static void printDivider() {
        System.out.println("\t____________________________________________________________");
    }

    private static Task createTask(String cmd) {
        String[] cmdArgs = cmd.split(" ");
        switch (cmdArgs[0]) {
        case "todo":
            return createTodo(cmdArgs);
        default:
            System.err.println("Unknown task type!");
            return null;
        }
    }

    private static Todo createTodo(String[] cmdArgs) {
        assert cmdArgs[0].equals("todo");
        StringBuilder content = new StringBuilder();
        for (int i = 1; i < cmdArgs.length; ++i) {
            content.append(cmdArgs[i]).append(" ");
        }
        return new Todo(content.toString());
    }


    private static void addTask(String cmd) {
        Task task = createTask(cmd);
        taskList.add(task);
        System.out.println("\t Got it, I have added this task: ");
        System.out.println("\t\t" + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list. ");
    }

    private static void markTask(int listId) {
        int index = listId - 1;
        if (index < 0 || index >= taskList.size()) {
            System.err.println("\t Index out of range!");
        } else {
            taskList.get(index).mark();
        }
    }

    private static void unmarkTask(int listId) {
        int index = listId - 1;
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

        // greeting messages
        printDivider();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        printDivider();

        // user interactions
        while (true) {
            String cmd = scanner.nextLine();
            String[] cmdArgs = cmd.split(" ");
            printDivider();

            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                listTasks();
            } else if (cmdArgs[0].equals("mark")) {
                markTask(Integer.parseInt(cmdArgs[1]));
            } else if (cmdArgs[0].equals("unmark")) {
                unmarkTask(Integer.parseInt(cmdArgs[1]));
            } else {
                addTask(cmd);
            }
            printDivider();
        }

        // exit message
        System.out.println("\t Bye. Hope to see you again soon!");
        printDivider();
    }
}
