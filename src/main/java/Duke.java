import exception.DukeException;
import exception.ErrorMessage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Scanner;
import java.util.ArrayList;


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

    public static void processCommands(ArrayList<Task> tasks, String userInput) throws DukeException {
        String[] words = userInput.split(" ");

        if (words[0].equals("list")) {
            listCommand(tasks);
        } else if (words[0].equals("todo")) {
            todoCommand(tasks, userInput);
        } else if (words[0].equals("event")) {
            eventCommand(tasks, userInput);
        } else if (words[0].equals("deadline")) {
            deadlineCommand(tasks, userInput);
        } else if (words[0].equals("delete")) {
            deleteCommand(tasks, words);
        } else if (words[0].contains("mark")) {
            markUnmarkCommand(tasks, words);
        } else {
            throw new DukeException(ErrorMessage.INVALID_COMMAND.toString());
        }
    }

    private static void listCommand(ArrayList<Task> tasks) throws DukeException {
        if (tasks.size() > 0) {
            horizontalLine();
            for (Task task : tasks) {
                System.out.println((tasks.indexOf(task) + 1) + "." + task);
            }
            horizontalLine();
        } else {
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
    }

    private static void todoCommand(ArrayList<Task> tasks, String userInput) throws DukeException {
        try {
            userInput = userInput.substring(5);
            tasks.add(new Todo(userInput));
            taskAdded(tasks);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.MISSING_TODO_PARAMETER.toString());
        }
    }

    private static void eventCommand(ArrayList<Task> tasks, String userInput) throws DukeException {
        try {
            int positionOfSlash = userInput.indexOf("/");
            if (positionOfSlash == -1) {
                throw new DukeException(ErrorMessage.MISSING_TWO_EVENT_PARAMETER.toString());
            }
            String startEnd = userInput.substring(positionOfSlash + 1);
            positionOfSlash = startEnd.indexOf("/");
            if (positionOfSlash == -1) {
                throw new DukeException(ErrorMessage.MISSING_ONE_EVENT_PARAMETER.toString());
            }
            String start = startEnd.substring(5, positionOfSlash - 1);
            String end = startEnd.substring(positionOfSlash + 4);

            String description = userInput.substring(6, positionOfSlash - 1);
            tasks.add(new Event(description, start, end));
            taskAdded(tasks);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.MISSING_EVENT_PARAMETER.toString());
        }
    }

    private static void deadlineCommand(ArrayList<Task> tasks, String userInput) throws DukeException {
        int positionOfBy = userInput.indexOf("/by");
        if (positionOfBy == -1) {
            throw new DukeException(ErrorMessage.MISSING_DEADLINE_BY_PARAMETER.toString());
        }

        try {
            String by = userInput.substring(positionOfBy + 4);
            try {
                String description = userInput.substring(9, positionOfBy - 1);
                tasks.add(new Deadline(description, by));
                taskAdded(tasks);
            } catch (IndexOutOfBoundsException error) {
                throw new DukeException(ErrorMessage.MISSING_DEADLINE_PARAMETER.toString());
            }
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.EMPTY_DEADLINE_BY_PARAMETER.toString());
        }
    }

    public static void taskAdded(ArrayList<Task> tasks) {
        horizontalLine();
        boolean lessThanOne = (tasks.size() <= 1);
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + (lessThanOne ? " task" : " tasks") + " in the list");
        horizontalLine();
    }

    private static void deleteCommand(ArrayList<Task> tasks, String[] words) throws DukeException {
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > tasks.size()) {
            throw new DukeException(ErrorMessage.INVALID_DELETE.toString());
        }
        Task taskToRemove = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        taskRemoved(tasks, taskToRemove);
    }

    public static void taskRemoved(ArrayList<Task> tasks, Task t) {
        horizontalLine();
        boolean isLessThanOne = (tasks.size() <= 1);
        System.out.println("Got it. I've removed this task:\n" + t.toString());
        System.out.println("Now you have " + tasks.size() + (isLessThanOne ? " task" : " tasks") + " in the list\n");
        horizontalLine();
    }

    private static void markUnmarkCommand(ArrayList<Task> tasks, String[] words) throws DukeException {
        horizontalLine();
        int taskNumber = Integer.parseInt(words[1]);
        if (taskNumber > tasks.size()) {
            throw new DukeException(ErrorMessage.INVALID_TASK.toString());
        }

        if (words[0].equals("mark")) {
            tasks.get(taskNumber - 1).markDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks.get(taskNumber - 1).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(tasks.get(taskNumber - 1));
        horizontalLine();
    }

    public static void main(String[] args) {
        logoWithHello();
        ArrayList<Task> tasks = new ArrayList<>();

        greet();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }

            try {
                processCommands(tasks, userInput);
            } catch (Exception error) {
                horizontalLine();
                System.out.println("Error message: " + error.getMessage());
                horizontalLine();
            }
        }
        exit();
    }
}
