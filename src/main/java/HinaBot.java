import java.util.Scanner;

public class HinaBot {
    protected static String[] taskList = new String[100];
    protected static int taskCount = 0;
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        showGreeting();
        while (true) {
            line = in.nextLine();
            handleCommand(line);
        }
    }

    private static void showGreeting() {
        System.out.println("Hello master!");
        System.out.println("What are your orders?");
    }

    public static void handleCommand(String command) {
        if (command.equalsIgnoreCase("bye")) {
            System.out.println("Goodbye master, let's meet again soon...");
            System.exit(0);
        }
        else if (command.equalsIgnoreCase("list")) {
            listTasks();
        }
        else {
            addTask(command);
        }
    }

    public static void addTask(String task) {
        taskList[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task);
    }

    public static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.println(". " + taskList[i]);
        }
    }
}
