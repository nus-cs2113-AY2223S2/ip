package duke;

import command.Command;
import exception.CommandNotRecognisedException;
import exception.EmptyTaskException;
import exception.IllegalCharacterException;
import exception.InvalidTaskNumberException;

/**
 * This class takes in user input from the CLI and attempts to make sense of it. It extracts command words and process
 * the input according to it.
 */
public class Parser {

    private final Ui ui = new Ui();

    /**
     * This method takes in user input from the CLI and attempts to make sense of it. It filters the input according to
     * the command words such as 'list', 'mark' and 'event' etc and passes them to their respective methods to carry out
     * the command.
     *
     * @param input This is the input from the CLI.
     * @param taskList The list where all tasks are stored.
     * @throws CommandNotRecognisedException User did not enter a valid command.
     * @throws IllegalCharacterException User inputted '|' or '-'.
     */
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

    /**
     * This method test if the input by the user is valid for the <bold>'delete'</bold> command which syntax is
     * "<code>delete <italics>taskNumber</italics></code>". If it is valid, it calls the deleteTask method from
     * the class taskList to delete task based on the position in the taskList.
     *
     * @param input Contains the command type followed by the index of task to delete.
     * @param taskList The list where all tasks are stored.
     */
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

    /**
     * This method test if the input by the user is valid for the <bold>'event'</bold> command which syntax is
     * "<code>event <italics>taskDesc</italics> /from <italics>fromDate</italics> /to <italics>toDate</italics></code>".
     * If it is valid, it will extract the 'from' date, 'to' date, and description of task and call addEventTask from
     * the class taskList.
     *
     * @param input Contains the command type followed by the task description and time range.
     * @param taskList The list where all tasks are stored.
     */
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

    /**
     * This method test if the input by the user is valid for the <bold>deadline</bold> command which syntax is
     * "<code>deadline <italics>taskDesc</italics> /by <italics>byDate</italics></code>". If it is valid, it will
     * extract the 'by' date and description of task and call addDeadlineTask from the class taskList.
     *
     * @param input Contains the command type followed by the task description and deadline.
     * @param taskList The list where all tasks are stored.
     */
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

    /**
     * This method test if the input by the user is valid for the <bold>todo</bold> command which syntax is
     * "<code>todo <italics>taskDesc</italics> </code>". If it is valid, it will extract the description of task
     * and call addTodoTask from the class taskList.
     *
     * @param input Contains the command type followed by the task description and deadline.
     * @param taskList The list where all tasks are stored.
     */
    private void processCommandTodo(String input, TaskList taskList) {
        try {
            taskList.addTodoTask(input.substring(Command.COMMAND_TODO.length()).trim());
            taskList.printTaskAdded();
        } catch (EmptyTaskException e) {
            System.out.println("☹ OOPS!!! The description of 'todo' cannot be empty.");
        }
        ui.printDivider();
    }

    /**
     * This method test if the input by the user is valid for the <bold>unmark</bold> command which syntax is
     * "<code>ummark <italics>taskNumber</italics></code>". If it is valid, it calls the markTaskUndone method from
     * the class taskList to unmark task based on the position in the taskList.
     *
     * @param input Contains the command type followed by the index of task.
     * @param taskList The list where all tasks are stored.
     */
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

    /**
     * This method test if the input by the user is valid for the <bold>mark</bold> command which syntax is
     * "<code>mark <italics>taskNumber</italics></code>". If it is valid, it calls the markTaskDone method from
     * the class taskList to mark task based on the position in the taskList.
     *
     * @param input Contains the command type followed by the index of task.
     * @param taskList The list where all tasks are stored.
     */
    private void processCommandMark(String input, TaskList taskList) {
        try {
            taskList.markTaskDone(Integer.parseInt(input.split(" ")[1]) - 1);
            taskList.tasks.get(Integer.parseInt(input.split(" ")[1].trim())-1).printMarkMessage();
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
