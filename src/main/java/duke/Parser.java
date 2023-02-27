package duke;

import command.Command;
import exception.CommandNotRecognisedException;
import exception.EmptyTaskException;
import exception.IllegalCharacterException;
import exception.InvalidTaskNumberException;
import task.Task;

import java.util.ArrayList;

public class Parser {

    public static void processCommand(String input, ArrayList<Task> tasks) throws CommandNotRecognisedException, IllegalCharacterException {
        if (input.contains("|") || input.contains("-")) {
            throw new IllegalCharacterException();
        }

        String action = input.split(" ")[0];

        switch (action) {
        case Command.COMMAND_BYE:
            Ui.printBye();
            break;
        case Command.COMMAND_LIST:
            TaskList.printTaskList();
            break;
        case Command.COMMAND_MARK:
            processCommandMark(input, tasks);
            break;
        case Command.COMMAND_UNMARK:
            processCommandUnmark(input, tasks);
            break;
        case Command.COMMAND_TODO:
            processCommandTodo(input);
            break;
        case Command.COMMAND_DEADLINE:
            processCommandDeadline(input);
            break;
        case Command.COMMAND_EVENT:
            processCommandEvent(input);
            break;
        case Command.COMMAND_DELETE:
            processCommandDelete(input);
            break;
        default:
            throw new CommandNotRecognisedException();
        }
    }

    private static void processCommandDelete(String input) {
        try {
            TaskList.deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number should be an integer.");
        } catch (InvalidTaskNumberException e) {
            System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");

        }
        Ui.printDivider();
    }

    private static void processCommandEvent(String input) {
        String[] taskDesc;
        try {
            taskDesc = input.split("/from|/to");
            TaskList.addEventTask(taskDesc[0].substring(Command.COMMAND_EVENT.length()).trim()
                    , taskDesc[1].trim(), taskDesc[2].trim());
            TaskList.printTaskAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'event' should include a task and time period.");
        }
        Ui.printDivider();
    }

    private static void processCommandDeadline(String input) {
        String[] taskDesc;
        try {
            taskDesc = input.split("/by");
            TaskList.addDeadlineTask(taskDesc[0].substring(Command.COMMAND_DEADLINE.length()).trim(), taskDesc[1].trim());
            TaskList.printTaskAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'deadline' should include a task and deadline.");
        }
        Ui.printDivider();
    }

    private static void processCommandTodo(String input) {
        try {
            TaskList.addTodoTask(input.substring(Command.COMMAND_TODO.length()).trim());
            TaskList.printTaskAdded();
        } catch (EmptyTaskException e) {
            System.out.println("☹ OOPS!!! The description of 'todo' cannot be empty.");
        }
        Ui.printDivider();
    }

    private static void processCommandUnmark(String input, ArrayList<Task> tasks) {
        try {
            TaskList.markTaskUndone(Integer.parseInt(input.split(" ")[1].trim())-1);
            tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number should be an integer.");
        } catch (InvalidTaskNumberException e) {
            System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'unmark' cannot be empty.");
        }
        Ui.printDivider();
    }

    private static void processCommandMark(String input, ArrayList<Task> tasks) {
        try {
            TaskList.markTaskDone(Integer.parseInt(input.split(" ")[1]) - 1);
            tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number should be an integer.");
        } catch (InvalidTaskNumberException e) {
            System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
        }
        Ui.printDivider();
    }
}
