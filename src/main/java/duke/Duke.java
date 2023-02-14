package duke;

import duke.task.Task;

import java.util.Scanner;

public class Duke {

    public static final String LINE_BREAK = "    ____________________________________________________________";
    public static final String INDENTATION = "    ";

    public static final String HELP_PAGE = "    todo: add a new task to Duke\n" +
            "    deadline: add a new task and '/by' date to add a task with deadline\n" +
            "    event: add a new event with '/from' and '/to' duration\n" +
            "    list: list out all tasks stored\n" +
            "    help: no :D\n    Please enter command:\n" +
            "    bye: end the program";

    public static void main(String[] args) {
        System.out.println(LINE_BREAK);
        printlnWithIndentation("Hello! I'm Duke");
        printlnWithIndentation("What can I do for you?");
        System.out.println(LINE_BREAK);
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        while (true) {
            userInput = in.nextLine();
            String[] inputLine = userInput.split(" ", 2);
            String command = inputLine[0];

            if (command.equals("bye")) {
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Bye. Hope to see you again soon!");
                System.out.println(LINE_BREAK);
                // exit while loop and end program
                break;
            } else if (command.equals("list")) {
                // list out the tasks and status
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Here are the tasks in your list: ");
                for (int i = 0; i < taskIndex; ++i) {
                    int taskNumber = i + 1;
                    System.out.println(INDENTATION + taskNumber + "." +
                            tasks[i].toString());
                }
                System.out.println(LINE_BREAK);
                continue;
            }

            try {
                switch (command) {
                case "mark":
                    commandMark(tasks, inputLine);
                    break;
                case "unmark":
                    commandUnmark(tasks, inputLine);
                    break;
                case "todo":
                    // command todo
                    taskIndex = commandTodo(tasks, taskIndex, inputLine);
                    break;
                case "deadline":
                    taskIndex = commandDeadline(tasks, taskIndex, inputLine);
                    break;
                case "event":
                    taskIndex = commandEvent(tasks, taskIndex, inputLine);
                    break;
                case "help":
                    System.out.println(HELP_PAGE);
                    break;
                default:
                    throw new IllegalCommandException(command);
                }
            } catch (IllegalCommandException e) {
                System.out.println(LINE_BREAK);
                printlnWithIndentation("INVALID COMMAND!");
                System.out.println(HELP_PAGE + LINE_BREAK);
            }
        }
    }

    private static int commandEvent(Task[] tasks, int taskIndex, String[] inputLine) {
        try {
            String action = inputLine[1];
            if (action.contains("/from") & action.contains("/to")) {
                int fromIndex = action.indexOf("/from");
                int toIndex = action.indexOf("/to");
                String toAddEvent = action.substring(0, fromIndex - 1);
                String fromTime = action.substring(fromIndex + 5, toIndex - 1);
                String toTime = action.substring(toIndex + 3);
                toAddEvent = toAddEvent.trim();
                fromTime = fromTime.trim();
                toTime = toTime.trim();
                Event addEvent = new Event(toAddEvent, fromTime, toTime);
                tasks[taskIndex] = addEvent;
                taskIndex++;
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Got it. I've added this task: ");
                System.out.println(INDENTATION + "  " + addEvent.toString());
                System.out.println(INDENTATION + "Now you have " + taskIndex + " tasks in the list. \n" + LINE_BREAK);
            } else {
                throw new IllegalCommandException(action);
            }
        } catch (IllegalCommandException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("INVALID COMMAND! Missing '/from' or '/to'");
            System.out.println(HELP_PAGE + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE_BREAK);
            System.out.println(INDENTATION + "event cannot be empty");
            System.out.println(LINE_BREAK);
        }
        return taskIndex;
    }

    private static int commandDeadline(Task[] tasks, int taskIndex, String[] inputLine) {
        try {
            String action = inputLine[1];
            if (action.contains("/by")) {
                int byIndex = action.indexOf("/by");
                String toAddDeadline = action.substring(0, byIndex - 1);
                String by = action.substring(byIndex + 3);
                toAddDeadline = toAddDeadline.trim();
                by = by.trim();
                Deadline addDeadline = new Deadline(toAddDeadline, by);
                tasks[taskIndex] = addDeadline;
                taskIndex++;
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Got it. I've added this task: ");
                System.out.println(INDENTATION + "  " + addDeadline.toString());
                System.out.println(INDENTATION + "Now you have " + taskIndex + " tasks in the list. \n" + LINE_BREAK);
            } else {
                throw new IllegalCommandException(action);
            }
        } catch (IllegalCommandException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("INVALID COMMAND! Missing '/by'");
            System.out.println(HELP_PAGE + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("deadline cannot be empty");
            System.out.println(LINE_BREAK);
        }
        return taskIndex;
    }

    private static int commandTodo(Task[] tasks, int taskIndex, String[] inputLine) {
        try {
            String action = inputLine[1];
            action = action.trim();
            Todo addTodo = new Todo(action);
            tasks[taskIndex] = addTodo;
            taskIndex++;
            System.out.println(LINE_BREAK);
            printlnWithIndentation("Got it. I've added this task: ");
            System.out.println(INDENTATION + "  " + addTodo.toString());
            System.out.println(INDENTATION + "Now you have " +
                    taskIndex + " tasks in the list. \n" + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("todo cannot be empty");
            System.out.println(LINE_BREAK);
        }
        return taskIndex;
    }

    private static void commandUnmark(Task[] tasks, String[] inputLine) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToMark = Integer.parseInt(action) - 1;
            tasks[indexToMark].setDone(false);
            printlnWithIndentation("OK, I've marked this task as not done yet: ");
            System.out.println(INDENTATION + "  [ ] " + tasks[indexToMark].getTaskDescription() + '\n' + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("unmark cannot be empty");
            System.out.println(LINE_BREAK);
        } catch (NullPointerException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("Invalid task number");
            System.out.println(LINE_BREAK);
        }
    }

    private static void commandMark(Task[] tasks, String[] inputLine) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToMark = Integer.parseInt(action) - 1;
            tasks[indexToMark].setDone(true);
            printlnWithIndentation("Nice! I've marked this task as done:");
            System.out.println(INDENTATION + "  [X] " + tasks[indexToMark].getTaskDescription() + '\n' + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("mark cannot be empty");
            System.out.println(LINE_BREAK);
        } catch (NullPointerException e) {
            System.out.println(LINE_BREAK);
            printlnWithIndentation("Invalid task number");
            System.out.println(LINE_BREAK);
        }
    }

    static void printlnWithIndentation(String string) {
        System.out.println(INDENTATION + string);
    }

}
