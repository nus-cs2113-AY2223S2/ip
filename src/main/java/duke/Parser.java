package duke;

import command.Command;
import exception.CommandNotRecognisedException;
import exception.EmptyTaskException;
import exception.IllegalCharacterException;
import exception.InvalidTaskNumberException;

public class Parser {

    private final Ui ui = new Ui();

    public void processCommand(String input, TaskList taskList) throws CommandNotRecognisedException, IllegalCharacterException {

        if (input.contains("|") || input.contains("-")) {
            throw new IllegalCharacterException();
        }

        String action = input.split(" ")[0];

        switch (action) {
        case Command.COMMAND_BYE:
            ui.printBye();
            break;
        case Command.COMMAND_LIST:
            taskList.printTaskList();
            break;
        case Command.COMMAND_MARK:
            processCommandMark(input, taskList);
            break;
        case Command.COMMAND_UNMARK:
            processCommandUnmark(input, taskList);
            break;
        case Command.COMMAND_TODO:
            processCommandTodo(input, taskList);
            break;
        case Command.COMMAND_DEADLINE:
            processCommandDeadline(input, taskList);
            break;
        case Command.COMMAND_EVENT:
            processCommandEvent(input, taskList);
            break;
        case Command.COMMAND_DELETE:
            processCommandDelete(input, taskList);
            break;
        case Command.COMMAND_FIND:
            processCommandFind(input, taskList);
            break;
        default:
            throw new CommandNotRecognisedException();
        }
    }

    private void processCommandDelete(String input, TaskList taskList) {
        try {
            taskList.deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number should be an integer.");
        } catch (InvalidTaskNumberException e) {
            System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");

        }
        ui.printDivider();
    }

    private void processCommandEvent(String input, TaskList taskList) {
        String[] taskDesc;
        try {
            taskDesc = input.split("/from|/to");
            taskList.addEventTask(taskDesc[0].substring(Command.COMMAND_EVENT.length()).trim()
                    , taskDesc[1].trim(), taskDesc[2].trim());
            taskList.printTaskAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'event' should include a task and time period.");
        }
        ui.printDivider();
    }

    private void processCommandDeadline(String input, TaskList taskList) {
        String[] taskDesc;
        try {
            taskDesc = input.split("/by");
            taskList.addDeadlineTask(taskDesc[0].substring(Command.COMMAND_DEADLINE.length()).trim(), taskDesc[1].trim());
            taskList.printTaskAdded();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'deadline' should include a task and deadline.");
        }
        ui.printDivider();
    }

    private void processCommandTodo(String input, TaskList taskList) {
        try {
            taskList.addTodoTask(input.substring(Command.COMMAND_TODO.length()).trim());
            taskList.printTaskAdded();
        } catch (EmptyTaskException e) {
            System.out.println("☹ OOPS!!! The description of 'todo' cannot be empty.");
        }
        ui.printDivider();
    }

    private void processCommandUnmark(String input, TaskList taskList) {
        try {
            taskList.markTaskUndone(Integer.parseInt(input.split(" ")[1].trim())-1);
            taskList.tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number should be an integer.");
        } catch (InvalidTaskNumberException e) {
            System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'unmark' cannot be empty.");
        }
        ui.printDivider();
    }

    private void processCommandMark(String input, TaskList taskList) {
        try {
            taskList.markTaskDone(Integer.parseInt(input.split(" ")[1]) - 1);
            taskList.tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printUnmarkMessage();
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Task number should be an integer.");
        } catch (InvalidTaskNumberException e) {
            System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
        }
        ui.printDivider();
    }

    private void processCommandFind(String input, TaskList taskList) {
        try {
            taskList.findTasks(input.substring(Command.COMMAND_TODO.length()).trim());
        } catch (EmptyTaskException e) {
            System.out.println("☹ OOPS!!! The description of 'find' cannot be empty.");
        }
        ui.printDivider();
    }
}
