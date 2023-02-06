package duke;

import duke.command.Command;
import duke.exception.CommandNotRecognisedException;
import duke.exception.InvalidTaskNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
public class Duke {

    public static final int TASK_LIST_SIZE = 100;
    public static Task[] tasks = new Task[TASK_LIST_SIZE];
    public static int taskCounter = 0;

    public static void main(String[] args) {
        printGreeting();

        String input;
        Scanner in = new Scanner(System.in);

        do {
            input = in.nextLine();
            try {
                processCommand(input);
            } catch (CommandNotRecognisedException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printDivider();
            }
        } while (!input.equals(Command.COMMAND_BYE));
    }

    public static void printDivider () {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting () {
        String logo = " ____\n"
                + "|    \\ ___ _ _ ___\n"
                + "|  |  | .'| | | -_|\n"
                + "|____/|__,|\\_/|___|\n";

        printDivider();
        System.out.print(logo);
        System.out.println("Hi, I'm Dave!\n"
                + "What can I do for you?");
        printDivider();
    }

    public static void processCommand (String input) throws CommandNotRecognisedException {
        String action = input.split(" ")[0];
        switch (action) {
        case Command.COMMAND_BYE:
            printBye();
            break;
        case Command.COMMAND_LIST:
            printTaskList();
            break;
        case Command.COMMAND_MARK:
            try {
                markTaskDone(Integer.parseInt(input.split(" ")[1]) - 1);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! duke.task.Task number should be an integer.");
                printDivider();
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
                printDivider();
            }
            break;
        case Command.COMMAND_UNMARK:
            try {
                markTaskUndone(Integer.parseInt(input.split(" ")[1]) - 1);
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! duke.task.Task number should be an integer.");
                printDivider();
            } catch (InvalidTaskNumberException e) {
                System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
                printDivider();
            }
            break;
        case Command.COMMAND_TODO:
            try {
                addTodoTask(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'todo' cannot be empty.");
                printDivider();
            }
            break;
        case Command.COMMAND_DEADLINE:
            try {
                addDeadlineTask(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'deadline' should include a task and deadline.");
                printDivider();
            }
            break;
        case Command.COMMAND_EVENT:
            try {
                addEventTask(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of 'event' should include a task and time period.");
                printDivider();
            }
            break;
        default:
            throw new CommandNotRecognisedException();
        }
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    private static void printTaskList() {
        if (taskCounter == 0) {
            System.out.println("You are free today :)");
        } else {
            for (int i = 0; i < taskCounter; ++i) {
                System.out.print(i + 1 + ".");
                System.out.println(tasks[i]);
            }
        }
        printDivider();
    }

    private static void markTaskDone(int taskIndex) throws InvalidTaskNumberException {
                if (taskIndex < 0 || taskIndex >= taskCounter) {
                    throw new InvalidTaskNumberException();
                } else {
                    tasks[taskIndex].markDone();
                }
                printDivider();
    }

    private static void markTaskUndone(int taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex >= taskCounter) {
            throw new InvalidTaskNumberException();
        } else {
            tasks[taskIndex].markUndone();
        }
        printDivider();
    }

    private static void addTodoTask(String input) {
            tasks[taskCounter] = new ToDo(input.substring(Command.COMMAND_TODO.length() + 1));
            taskCounter++;
            printTaskAdded();
    }

    private static void addDeadlineTask(String input) {
            int indexOfBy = input.indexOf(Command.COMMAND_DEADLINE_BY);
            tasks[taskCounter] = new Deadline(input.substring(Command.COMMAND_DEADLINE.length() + 1, indexOfBy)
                    , input.substring(indexOfBy + Command.COMMAND_DEADLINE_BY.length()));
            taskCounter++;
            printTaskAdded();
    }

    private static void addEventTask(String input) {
        int indexOfFrom = input.indexOf(Command.COMMAND_EVENT_FROM);
        int indexOfTo = input.indexOf(Command.COMMAND_EVENT_TO);
        tasks[taskCounter] = new Event(input.substring(Command.COMMAND_EVENT.length() + 1, indexOfFrom)
                , input.substring(indexOfFrom + Command.COMMAND_EVENT_FROM.length(), indexOfTo)
                , input.substring(indexOfTo + Command.COMMAND_EVENT_TO.length()));
        taskCounter++;
        printTaskAdded();
    }

    private static void printTaskAdded() {
        System.out.println("Got it. I've added this task:\n  " + tasks[taskCounter -1]
                + "\nNow you have " + taskCounter + " tasks in the list.");
        printDivider();
    }

}
