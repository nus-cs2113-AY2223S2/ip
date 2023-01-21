import java.util.Scanner;

public class Duke {
    public static void printDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
    }

    public static String breakLine() {
        return "------------------------------------------\n";
    }

    // Greet
    public static void greet() {
        System.out.println(breakLine()
                + "Hello! I'm Momo :)\n"
                + "What can I do for you?\n"
                + breakLine());
    }

    // add a new task
    public static void addTask(Task[] tasks, int totalTasks, String description) {
        tasks[totalTasks] = new Task(description);
        System.out.print(breakLine()
                + "added: " + description + '\n'
                + breakLine());
    }

    // list all tasks
    public static void listTask(Task[] tasks, int countTasks) {
        System.out.print(breakLine());
        for (int i = 0; i < countTasks; i++) {
            Task currentTask = tasks[i];
            System.out.print((i + 1) + ". " + currentTask.getStatusIcon() + ' ' + currentTask.description + '\n');
        }
        System.out.print(breakLine());
    }

    //mark the task
    public static void markTask(Task[] tasks, int taskNumber) {
        Task currentTask = tasks[taskNumber];
        currentTask.markAsDone();
        System.out.print(breakLine()
                + "Wow! I've marked this task as done :D\n"
                + currentTask.getStatusIcon() + " " + currentTask.description + '\n'
                + breakLine());
    }

    // unmark the task
    public static void unmarkTask(Task[] tasks, int taskNumber) {
        Task currentTask = tasks[taskNumber];
        currentTask.unmarkAsNotDone();
        System.out.print(breakLine()
                + "Oh. I've unmarked this task as not done yet :(\n"
                + currentTask.getStatusIcon() + " " + currentTask.description + '\n'
                + breakLine());
    }

    // exit
    public static void exit() {
        System.out.print(breakLine()
                + "Ba-bye. Hope to see you again soon :)\n"
                + breakLine());
    }

    // read input
    public static String readInput() {
        System.out.print(">> ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void printOut(String str) {
        System.out.println("# " + str);
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int totalTasks = 0;

        printDuke();
        greet();
        String input = readInput();
        String[] splitedInput = input.split(" ");
        while (!splitedInput[0].equals("bye")) {
            if (input.equals("list")) {
                listTask(tasks, totalTasks);
            } else if (splitedInput[0].equals("mark")) {
                markTask(tasks, Integer.parseInt(splitedInput[1]) - 1);
            } else if (splitedInput[0].equals("unmark")) {
                unmarkTask(tasks, Integer.parseInt(splitedInput[1]) - 1);
            } else {
                addTask(tasks, totalTasks, input);
                totalTasks++;
            }
            input = readInput();
            splitedInput = input.split(" ");
        }
        exit();
    }
}
