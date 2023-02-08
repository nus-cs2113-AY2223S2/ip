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
        startDuke();
        exitDuke();
    }

    private static void greetUser() {
        System.out.println(logo);
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
    }

    private static void exitDuke() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    private static Task createTask(String input) throws DukeException, ArrayIndexOutOfBoundsException {
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
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    private static boolean isTasksFree() {
        return taskCount < MAX_TASKS;
    }

    private static void addTask(Task task) throws DukeException {
        if (!isTasksFree()) {
            throw new DukeException("Too much tasks!");
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

    private static void markTask(String input) throws NumberFormatException {
        int taskNum = Integer.parseInt(input.split("mark ")[1]);
        if (!isValidTask(taskNum)) {
            throw new NumberFormatException();
        }
        tasks[taskNum - 1].markDone();
        System.out.println(line + "\nNice! I've marked this task as done:\n[" + tasks[taskNum - 1].getTaskType() +
                "][X] " + tasks[taskNum - 1].getDescription() + "\n" + line);
    }

    private static void unmarkTask(String input) throws NumberFormatException {
        int taskNum = Integer.parseInt(input.split("unmark ")[1]);
        if (!isValidTask(taskNum)) {
            throw new NumberFormatException();
        }
        tasks[taskNum - 1].unmarkDone();
        System.out.println(line + "\nOK, I've marked this task as not done yet:\n[" + tasks[taskNum - 1].getTaskType() +
                    "][" + tasks[taskNum - 1].getStatusIcon() + "] " + tasks[taskNum - 1].getDescription() + "\n" + line);
    }

    private static void doCommand(String input) throws DukeException {
        input = input.trim();
        String[] inputs = input.split(" ");
        String command = inputs[0];

        switch(command) {
        case "list":
            listTasks();
            break;
        case "mark":
            try {
                markTask(input);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number!");
            }
            break;
        case "unmark":
            try {
                unmarkTask(input);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number!");
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            try {
                Task newTask = createTask(input);
                addTask(newTask);
            } catch (DukeException e) {
                throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Missing details for task!");
            }
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void startDuke() {
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();

            if (input.equals("bye")) {
                return;
            }

            try {
                doCommand(input);
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                System.out.println(line + '\n' + errorMessage + '\n' + line);
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = "Wrong input format for task!";
                System.out.println(line + '\n' + errorMessage + '\n' + line);
            }
        }
    }
}
