package duke;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.Todo;
import duke.exception.IllegalCommandException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String LINE_BREAK = "    ____________________________________________________________";
    public static final String INDENTATION = "    ";

    public static final String HELP_PAGE = "    todo: add a new task to Duke\n" +
            "    deadline: add a new task and '/by' date to add a task with deadline\n" +
            "    event: add a new event with '/from' and '/to' duration\n" +
            "    list: list out all tasks stored\n" +
            "    help: no :D\n    bye: end the program\n    Please enter command:\n";


    public static void main(String[] args) {
        System.out.println(LINE_BREAK);
        printlnWithIndentation("Hello! I'm Duke");
        printlnWithIndentation("What can I do for you?");
        System.out.println(LINE_BREAK);
        Scanner in = new Scanner(System.in);
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();
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
                if (tasks.isEmpty()) {
                    // empty list -> print help
                    printException("There is nothing in your list right now.");
                } else {
                    System.out.println(LINE_BREAK);
                    printlnWithIndentation("Here are the tasks in your list: ");
                    for (int i = 0; i < tasks.size(); ++i) {
                        int taskNumber = i + 1;
                        System.out.println(INDENTATION + taskNumber + "." +
                                tasks.get(i).toString());
                    }
                    System.out.println(LINE_BREAK);
                }
                continue;
            }

            try {
                switch (command) {
                case "delete":
                    deleteTask(tasks, inputLine);
                    break;
                case "mark":
                    markTask(tasks, inputLine);
                    break;
                case "unmark":
                    unmarkTask(tasks, inputLine);
                    break;
                case "todo":
                    // command todo
                    makeTodo(tasks, inputLine);
                    break;
                case "deadline":
                    makeDeadline(tasks, inputLine);
                    break;
                case "event":
                    makeEvent(tasks, inputLine);
                    break;
                case "help":
                    System.out.println(HELP_PAGE);
                    break;
                default:
                    throw new IllegalCommandException(command);
                }
            } catch (IllegalCommandException e) {
                printException("INVALID COMMAND!");
            }
        }
    }

    private static void printException(String string) {
        System.out.println(LINE_BREAK);
        printlnWithIndentation(string);
        System.out.println(HELP_PAGE + LINE_BREAK);
    }

    private static void makeEvent(ArrayList<Task> tasks, String[] inputLine) {
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
                tasks.add(addEvent);
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Got it. I've added this task: ");
                System.out.println(INDENTATION + "  " + addEvent);
                printTaskCount(tasks);
            } else {
                throw new IllegalCommandException(action);
            }
        } catch (IllegalCommandException e) {
            printException("INVALID COMMAND! Missing '/from' or '/to'");
        } catch (ArrayIndexOutOfBoundsException e) {
            printException("event cannot be empty");
        }
    }

    private static void printTaskCount(ArrayList<Task> tasks) {
        // print out the number of task user has
        System.out.println(INDENTATION + "Now you have " + tasks.size() + " tasks in the list. \n" + LINE_BREAK);
    }

    private static void makeDeadline(ArrayList<Task> tasks, String[] inputLine) {
        try {
            String action = inputLine[1];
            if (action.contains("/by")) {
                int byIndex = action.indexOf("/by");
                String toAddDeadline = action.substring(0, byIndex - 1);
                String by = action.substring(byIndex + 3);
                toAddDeadline = toAddDeadline.trim();
                by = by.trim();
                Deadline addDeadline = new Deadline(toAddDeadline, by);
                tasks.add(addDeadline);
                System.out.println(LINE_BREAK);
                printlnWithIndentation("Got it. I've added this task: ");
                System.out.println(INDENTATION + "  " + addDeadline);
                printTaskCount(tasks);
            } else {
                throw new IllegalCommandException(action);
            }
        } catch (IllegalCommandException e) {
            printException("INVALID COMMAND! Missing '/by'");
        } catch (ArrayIndexOutOfBoundsException e) {
            printException("deadline cannot be empty");
        }
    }

    private static void makeTodo(ArrayList<Task> tasks, String[] inputLine) {
        try {
            String action = inputLine[1];
            action = action.trim();
            Todo addTodo = new Todo(action);
            tasks.add(addTodo);
            System.out.println(LINE_BREAK);
            printlnWithIndentation("Got it. I've added this task: ");
            System.out.println(INDENTATION + "  " + addTodo);
            printTaskCount(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            printException("todo cannot be empty");
        }
    }

    private static void unmarkTask(ArrayList<Task> tasks, String[] inputLine) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToMark = Integer.parseInt(action) - 1;
            tasks.get(indexToMark).setDone(false);
            printlnWithIndentation("OK, I've marked this task as not done yet: ");
            System.out.println(INDENTATION + "  [ ] " + tasks.get(indexToMark).getTaskDescription() + '\n' + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            printException("unmark cannot be empty");
        } catch (IndexOutOfBoundsException e) {
            printException("Invalid task number! Task number does not exist!");
        } catch (NumberFormatException e) {
            printException("Please input numeric number to unmark task!");
        }
    }

    private static void markTask(ArrayList<Task> tasks, String[] inputLine) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToMark = Integer.parseInt(action) - 1;
            tasks.get(indexToMark).setDone(true);
            printlnWithIndentation("Nice! I've marked this task as done:");
            System.out.println(INDENTATION + "  [X] " + tasks.get(indexToMark).getTaskDescription() + '\n' + LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            printException("mark cannot be empty");
        } catch (IndexOutOfBoundsException e) {
            printException("Invalid task number! Task number does not exist!");
        } catch (NumberFormatException e) {
            printException("Please input numeric number to mark task!");
        }
    }

    private static void deleteTask(ArrayList<Task> tasks, String[] inputLine) {
        try {
            String action = inputLine[1];
            action = action.trim();
            int indexToDelete = Integer.parseInt(action) - 1;
            printlnWithIndentation("Nice! I've deleted this task:");
            System.out.println(INDENTATION + tasks.get(indexToDelete).toString() +
                    '\n' + LINE_BREAK);
            tasks.remove(indexToDelete);
            printTaskCount(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            printException("delete cannot be empty");
        } catch (IndexOutOfBoundsException e) {
            printException("Invalid task number! Task number does not exist!");
        } catch (NumberFormatException e) {
            printException("Please input numeric number to delete task!");
        }
    }

    static void printlnWithIndentation(String string) {
        System.out.println(INDENTATION + string);
    }

}
