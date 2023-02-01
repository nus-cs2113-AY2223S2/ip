import java.util.Scanner;

public class HinaBot {
    protected static Task[] taskList = new Task[100];
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
        else if (command.split(" ")[0].equalsIgnoreCase("mark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            markTask(taskIndex);
        }
        else if (command.split(" ")[0].equalsIgnoreCase("unmark")) {
            int taskIndex = Integer.parseInt(command.split(" ")[1]);
            unmarkTask(taskIndex);
        }
        else {
            addTask(command);
        }
    }

    public static void addTask(String description) {
        Task newTask = new Task(description);
        taskList[taskCount] = newTask;
        taskCount++;
        System.out.println("added: " + description);
    }

    public static void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            if (taskList[i].isDone()) {
                System.out.print("[X] ");
            }
            else {
                System.out.print("[ ] ");
            }
            System.out.println(taskList[i].getDescription());
        }
    }

    public static void markTask(int taskIndex) {
        taskList[taskIndex - 1].setDone(true);
        System.out.println("Roger that! This task is marked as done: ");
        System.out.println("[X] " + taskList[taskIndex - 1].getDescription());
    }

    public static void unmarkTask(int taskIndex) {
        taskList[taskIndex - 1].setDone(false);
<<<<<<< HEAD
        System.out.println("Roger that! This task is marked as done: ");
=======
        System.out.println("Roger that! This task is marked as not done: ");
>>>>>>> bab6548 (Add Task class, add code to mark and unmark tasks)
        System.out.println("[ ] " + taskList[taskIndex - 1].getDescription());
    }
}
