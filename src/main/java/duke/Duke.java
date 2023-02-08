package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String LINE = "\t____________________________________________________________";
    static final String COMMAND_BYE = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";
    static final String COMMAND_EVENT = "event";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DELETE = "delete";
    static final String COMMAND_DEADLINE = "deadline";
    static ArrayList<Task> tasks = new ArrayList<>();
    static int taskCount = 0;

    public static void printInvalidNumber(String taskType) {
        System.out.println(LINE);
        System.out.println("\t☹ Error! Invalid input.");
        System.out.println("\tPlease provide a integer number for \"" + taskType + "\" command.");
        System.out.println("\tPlease use \"list\" command to see your task numbers.");
        System.out.println(LINE);
    }

    public static void printEmptyCommand(String taskType) {
        System.out.println(LINE);
        System.out.println("\t☹ Error! \"" + taskType + "\" command cannot be empty.");
        System.out.println("\tPlease provide more details");
        System.out.println(LINE);
    }

    public static void printInvalidFormat(String taskType) {
        System.out.println(LINE);
        System.out.println("\t☹ Error! Invalid format for \"" + taskType + "\" command.");
        System.out.println(LINE);
    }

    public static void doCommandGreet() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm Duke.");
        System.out.println("\tHow can I help you today?\n");
        System.out.println(LINE);
    }

    public static void doCommandBye() {
        System.out.println(LINE);
        System.out.println("\tBye! Remember to finish your tasks.\n");
        System.out.println(LINE);
    }

    public static void doCommandMark(int taskNum) {
        try {
            tasks.get(--taskNum).setStatus(true);
            System.out.println(LINE);
            System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
            System.out.println("\t  " + tasks.get(taskNum).getTaskNameAndStatus());
            System.out.println(LINE);
        } catch (IndexOutOfBoundsException | NullPointerException out_mark_b) {
            printInvalidNumber("mark");
        }
    }

    public static void doCommandUnmark(int taskNum) {
        try {
            tasks.get(--taskNum).setStatus(false);
            System.out.println(LINE);
            System.out.println("\tOh, ok. Task " + (taskNum + 1) + " has been marked as \"incomplete\":");
            System.out.println("\t  " + tasks.get(taskNum).getTaskNameAndStatus());
            System.out.println(LINE);
        } catch (IndexOutOfBoundsException | NullPointerException out_unmark_b) {
            printInvalidNumber("unmark");
        }
    }

    public static void doCommandList() {
        System.out.println(LINE);
        int count = 1;
        if (taskCount == 0) {
            System.out.println("\tYou have no pending tasks! ☺");
        } else {
            System.out.println("\tHere are your tasks:");
            for (int index = 0; index < taskCount; index++) {
                System.out.print("\t" + count + ".");
                System.out.println(tasks.get(index));
                count++;
            }
        }
        System.out.println(LINE);
    }

    public static void doCommandTodo(String taskName) {
        tasks.add(new Todo(taskName));
        taskCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks.get(taskCount - 1));
        System.out.println("\t" + "Now you have " + taskCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandDeadline(String taskName, String taskDeadline) {
        tasks.add(new Deadline(taskName, taskDeadline));
        taskCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks.get(taskCount - 1));
        System.out.println("\t" + "Now you have " + taskCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandEvent(String eventName, String eventDetailsPartOne, String eventDetailsPartTwo) {
        tasks.add(new Event(eventName, eventDetailsPartOne, eventDetailsPartTwo));
        taskCount++;
        System.out.println(LINE);
        System.out.println("\t" + "Task added!");
        System.out.println("\t  " + tasks.get(taskCount - 1));
        System.out.println("\t" + "Now you have " + taskCount + " pending tasks.");
        System.out.println(LINE);
    }

    public static void doCommandDelete(int taskNum) {
        System.out.println(LINE);
        System.out.println("\t" + "Task removed!");
        System.out.println("\t  " + tasks.get(taskNum - 1));
        System.out.println("\t" + "Now you have " + (taskCount - 1) + " pending tasks.");
        tasks.remove(taskNum - 1);
        System.out.println(LINE);
        taskCount--;
    }

    public static void handleUserCommand(String userCommand) {
        String[] extractFirstWord = userCommand.split(" ", 2);
        String firstWord = extractFirstWord[0];
        switch (firstWord) {
        case COMMAND_MARK:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                doCommandMark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_mark_a) {
                printInvalidNumber("mark");
            }
            break;
        case COMMAND_UNMARK:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                doCommandUnmark(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_unmark_a) {
                printInvalidNumber("unmark");
            }
            break;
        case COMMAND_LIST:
            doCommandList();
            break;
        case COMMAND_BYE:
            doCommandBye();
            break;
        case COMMAND_DELETE:
            try {
                int taskNum = Integer.parseInt(extractFirstWord[1]);
                doCommandDelete(taskNum);
            } catch (IndexOutOfBoundsException | NumberFormatException out_delete_a) {
                printInvalidNumber("delete");
            }
            break;
        case COMMAND_TODO:
            try {
                String taskName = (extractFirstWord[1]);
                doCommandTodo(taskName);
            } catch (IndexOutOfBoundsException out_todo_a) {
                printEmptyCommand("todo");
            }
            break;
        case COMMAND_DEADLINE:
            try {
                int index = extractFirstWord[1].indexOf("/by");
                String taskName = extractFirstWord[1].substring(0, index);
                String taskDeadline = extractFirstWord[1].substring(index + 4);
                doCommandDeadline(taskName, taskDeadline);
            } catch (StringIndexOutOfBoundsException out_deadline_a) {
                printInvalidFormat("deadline");
            } catch (IndexOutOfBoundsException out_deadline_a) {
                printEmptyCommand("deadline");
            }
            break;
        case COMMAND_EVENT:
            try {
                int indexOfEventDetailsPartOne = extractFirstWord[1].indexOf("/from");
                int indexOfEventDetailsPartTwo = extractFirstWord[1].indexOf("/to");
                String eventName = extractFirstWord[1].substring(0, indexOfEventDetailsPartOne);
                String eventDetailsPartOne = extractFirstWord[1].substring(indexOfEventDetailsPartOne + 6, indexOfEventDetailsPartTwo - 1);
                String eventDetailsPartTwo = extractFirstWord[1].substring(indexOfEventDetailsPartTwo + 4);
                doCommandEvent(eventName, eventDetailsPartOne, eventDetailsPartTwo);
            } catch (StringIndexOutOfBoundsException out_event_a) {
                printInvalidFormat("event");
            } catch (IndexOutOfBoundsException out_event_a) {
                printEmptyCommand("event");
            }
            break;
        default:
            System.out.println(LINE);
            System.out.println("\t☹ Error! Please input a valid command!");
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        doCommandGreet();
        Scanner in = new Scanner(System.in);
        String userCommand;
        do {
            userCommand = in.nextLine();
            handleUserCommand(userCommand);
        } while (!userCommand.equals(COMMAND_BYE));
    }
}
