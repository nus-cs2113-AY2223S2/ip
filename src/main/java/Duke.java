import java.util.Scanner;

public class Duke {
    public static final int MAXTASKSIZE = 100;

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
    public static void addTodo(Task[] tasks, int totalTasks, String taskInfo) {
        tasks[totalTasks] = new Todo(taskInfo);
        System.out.print(breakLine()
                + "added: \n\t" + tasks[totalTasks].toString() + '\n'
                + "(total: " + (totalTasks + 1) + " tasks)\n"
                + breakLine());
    }

    // add a new deadline
    public static void addDeadline(Task[] tasks, int totalTasks, String taskInfo) {
        String description, deadline;
        String[] info = taskInfo.split("#by", 2);
        description = info[0];
        deadline = info[1];
        tasks[totalTasks] = new Deadline(description, deadline);
        System.out.print(breakLine()
                + "added: \n\t" + tasks[totalTasks].toString() + '\n'
                + "(total: " + (totalTasks + 1) + " tasks)\n"
                + breakLine());
    }

    // add a new event
    public static void addEvent(Task[] tasks, int totalTasks, String taskInfo) {
        String description, from, to;
        String[] info = taskInfo.split("#from", 2);
        description = info[0];
        info = info[1].split("#to", 2);
        from = info[0];
        to = info[1];
        tasks[totalTasks] = new Event(description, from, to);
        System.out.print(breakLine()
                + "added: \n\t" + tasks[totalTasks].toString() + '\n'
                + "(total: " + (totalTasks + 1) + " tasks)\n"
                + breakLine());
    }

    // list all tasks
    public static void listTask(Task[] tasks, int totalTask) {
        System.out.print(breakLine());
        System.out.println("These are the tasks you have (" + totalTask + " tasks):");
        for (int i = 0; i < totalTask; i++) {
            Task currentTask = tasks[i];
            System.out.print((i + 1) + ". " + currentTask.toString() + '\n');
        }
        System.out.print(breakLine());
    }

    // mark the task
    public static void markTask(Task[] tasks, int taskNumber) {
        Task currentTask = tasks[taskNumber];
        currentTask.markAsDone();
        System.out.print(breakLine()
                + "Nice! I've marked this task as done :D\n"
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

    // read input
    public static String readInput() {
        System.out.print(">> ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    // exit
    public static void exit() {
        System.out.print(breakLine()
                + "Ba-bye. Hope to see you again soon :)\n"
                + breakLine());
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAXTASKSIZE];
        int totalTasks = 0;
        int taskNumber;
        String command, input, taskInfo = null;
        String[] splitedInput;

        printDuke();
        greet();

        while (true) {
            input = readInput();
            splitedInput = input.split(" ", 2);
            command = splitedInput[0];
            if (splitedInput.length > 1) {
                taskInfo = splitedInput[1];
            }

            switch (command) {
            case "bye":
                exit();
                break;
            case "list":
                listTask(tasks, totalTasks);
                break;
            case "mark":
                taskNumber = Integer.parseInt(taskInfo) - 1;
                markTask(tasks, taskNumber);
                break;
            case "unmark":
                taskNumber = Integer.parseInt(taskInfo) - 1;
                unmarkTask(tasks, taskNumber);
                break;
            case "todo":
                addTodo(tasks, totalTasks, taskInfo);
                totalTasks++;
                break;
            case "deadline":
                addDeadline(tasks, totalTasks, taskInfo);
                totalTasks++;
                break;
            case "event":
                addEvent(tasks, totalTasks, taskInfo);
                totalTasks++;
                break;
            }
        }
    }
}
