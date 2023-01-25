import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static String line = "____________________________________________________________\n";
    private static int taskCount = 0;
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void main(String[] args) {
        greetUser();
        doCommand();
    }

    private static void greetUser() {
        System.out.println(logo);
        System.out.println(line + "Hello! I'm Duke\nWhat can I do for you?\n" + line);
    }

    private static void exitDuke() {
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    private static void echoString(String input) {
        System.out.println(line + " " + input + "\n" + line);
    }

    private static void addTask(String text) {
        System.out.print(line);
        if (taskCount >= MAX_TASKS) {
            System.out.println("failed as task list is full\n" + line);
            return;
        }
        tasks[taskCount] = new Task(text);
        taskCount += 1;
        System.out.println("added: " + text);
        System.out.print(line);
    }

    private static void listTasks() {
        System.out.println(line + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(Integer.toString(i + 1) + ":[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());
        }
        System.out.print(line);
    }

    private static boolean isValidTask(int taskNum) {
        if (taskNum > taskCount || taskNum < 1) {
            return false;
        }
        return true;
    }

    private static void markTask(String input) {
        int taskNum = Integer.parseInt(input.substring(5));
        if (isValidTask(taskNum)) {
            tasks[taskNum - 1].markDone();
            System.out.print(line + "Nice! I've marked this task as done:\n[X] "
                    + tasks[taskNum - 1].getDescription() + "\n" + line);
        } else {
            System.out.print(line + "Invalid task number\n" + line);
        }
    }

    private static void unmarkTask(String input) {
        int taskNum = Integer.parseInt(input.substring(7));
        if (isValidTask(taskNum)) {
            tasks[taskNum - 1].unmarkDone();
            System.out.print(line + "OK, I've marked this task as not done yet:\n[ ] "
                    + tasks[taskNum - 1].getDescription() + "\n" + line);
        } else {
            System.out.print(line + "Invalid task number\n" + line);
        }
    }

    private static void doCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                exitDuke();
                return;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.matches("mark \\d+")) {
                markTask(input);
            } else if (input.matches("unmark \\d+")) {
                unmarkTask(input);
            } else {
                addTask(input);
            }
        }
    }
}
