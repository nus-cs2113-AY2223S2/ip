import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static void logoWithHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        horizontalLine();
    }

    public static void exit() {
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        horizontalLine();
    }

    public static void echo(String userInput) {
        horizontalLine();
        System.out.println("added: " + userInput);
        horizontalLine();
    }

    private static void listCommand(Task[] tasks) {
        horizontalLine();
        for (int i = 0; i < Task.getNumberOfTasks(); i += 1) {
            System.out.println(tasks[i].getTaskNumber() + "." + tasks[i]);
        }
        horizontalLine();
    }

    public static String[] processLine(String userInput) {
        String[] words = userInput.split(" ");
        return words;
    }

    public static void taskAdded(Task t) {
        horizontalLine();
        boolean lessThanOne = (Task.getNumberOfTasks() <= 1);
        System.out.println("Got it. I've added this task:\n" + t.toString());
        System.out.println("Now you have " + Task.getNumberOfTasks() + (lessThanOne ? " task" : " tasks") + " in the list\n");
        horizontalLine();
    }

    public static void processCommands(Task[] tasks, String userInput) {
        String[] words = processLine(userInput);
        if (words[0].equals("list")) {
            listCommand(tasks);
        } else if (words[0].equals("todo")) {
            todoCommand(tasks, userInput);
        } else if (words[0].equals("event")) {
            eventCommand(tasks, userInput);
        } else if (words[0].equals("deadline")) {
            deadlineCommand(tasks, userInput);
        } else if (words[0].contains("mark")) {
            markUnmarkCommand(tasks, words);
        }
    }

    private static void todoCommand(Task[] tasks, String userInput) {
        userInput = userInput.substring(5);
        tasks[Task.getNumberOfTasks()] = new Todo(userInput);
        taskAdded(tasks[Task.getNumberOfTasks() - 1]);
    }

    private static void eventCommand(Task[] tasks, String userInput) {
        int positionOfSlash = userInput.indexOf("/");
        String startEnd = userInput.substring(positionOfSlash + 1);
        String description = userInput.substring(6, positionOfSlash - 1);
        positionOfSlash = startEnd.indexOf("/");
        String start = startEnd.substring(5, positionOfSlash - 1);
        String end = startEnd.substring(positionOfSlash + 4);
        tasks[Task.getNumberOfTasks()] = new Event(description, start, end);
        taskAdded(tasks[Task.getNumberOfTasks() - 1]);
    }

    private static void deadlineCommand(Task[] tasks, String userInput) {
        int positionOfSlash = userInput.indexOf("/");
        String by = userInput.substring(positionOfSlash + 4);
        String description = userInput.substring(9, positionOfSlash - 1);
        tasks[Task.getNumberOfTasks()] = new Deadline(description, by);
        taskAdded(tasks[Task.getNumberOfTasks() - 1]);
    }

    private static void markUnmarkCommand(Task[] tasks, String[] words) {
        Integer taskNumber = Integer.parseInt(words[1]) - 1;
        horizontalLine();
        if (words[0].equals("mark")) {
            tasks[taskNumber].markDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks[taskNumber].markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks[taskNumber]);
        horizontalLine();
    }

    public static void main(String[] args) {
        logoWithHello();
        Task[] tasks = new Task[100];

        greet();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            processCommands(tasks, userInput);
        }
        exit();
    }


}
