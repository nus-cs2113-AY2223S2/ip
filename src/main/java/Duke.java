import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String line = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void main(String[] args) {
        greetUser();
        doCommand();
    }

    private static void greetUser() {
        System.out.println(logo);
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
    }

    private static void exitDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    private static Task createTask(String input) {
        String description;
        if (input.matches("todo .+")) {
            description = input.split("todo ")[1];
            return new Todo(description);
        } else if (input.matches("deadline .+")) {
            String removedKeyword = input.split("deadline ")[1];
            String[] splitString = removedKeyword.split(" /by ");
            description = splitString[0];
            String due = splitString[1];
            return new Deadline(description, due);
        } else if (input.matches("event .+")) {
            String removedKeyword = input.split("event ")[1];
            String[] splitString = removedKeyword.split(" /from ");
            description = splitString[0];
            splitString = splitString[1].split(" /to ");
            String start = splitString[0];
            String end = splitString[1];
            return new Event(description, start, end);
        } else {
            description = input;
            return new Task(description);
        }
    }

    private static boolean isTasksFree() {
        return taskCount < MAX_TASKS;
    }

    private static void addTask(Task task) {
        if (!isTasksFree()) {
            System.out.println(line + "\nfailed as tasks is full\n" + line);
            return;
        }
        tasks[taskCount] = task;
        taskCount += 1;
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getSummary());
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        System.out.println(line);
    }

    private static void listTasks() {
        System.out.println(line + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i+1);
            System.out.println(":" + tasks[i].getSummary());
        }
        System.out.println(line);
    }

    private static boolean isValidTask(int taskNum) {
        return taskNum <= taskCount && taskNum >= 1;
    }

    private static void markTask(String input) {
        int taskNum = Integer.parseInt(input.split("mark ")[1]);
        if (isValidTask(taskNum)) {
            tasks[taskNum - 1].markDone();
            System.out.println(line + "\nNice! I've marked this task as done:\n[" + tasks[taskNum - 1].getTaskType() +
                    "][X] " + tasks[taskNum - 1].getDescription() + "\n" + line);
        } else {
            System.out.println(line + "\nInvalid task number\n" + line);
        }
    }

    private static void unmarkTask(String input) {
        int taskNum = Integer.parseInt(input.split("unmark ")[1]);
        if (isValidTask(taskNum)) {
            tasks[taskNum - 1].unmarkDone();
            System.out.println(line + "\nOK, I've marked this task as not done yet:\n[" + tasks[taskNum - 1].getTaskType() +
                    "][" + tasks[taskNum - 1].getStatusIcon() + "] " + tasks[taskNum - 1].getDescription() + "\n" + line);
        } else {
            System.out.println(line + "\nInvalid task number\n" + line);
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
                Task newTask = createTask(input);
                addTask(newTask);
            }
        }
    }
}
