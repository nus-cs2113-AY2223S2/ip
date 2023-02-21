package luke;

import luke.command.AddCommand;
import luke.command.Command;
import luke.command.DeleteCommand;
import luke.command.EchoCommand;
import luke.command.FindCommand;
import luke.command.InvalidCommand;
import luke.command.ListCommand;
import luke.command.MarkCommand;
import luke.command.OutOfBoundsCommand;
import luke.command.TestingModeCommand;
import luke.command.UnmarkCommand;
import luke.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A <code>Parser</code> object is created to make sense of the commands keyed in by the user. It creates the
 * appropriate <code>Command</code> object to execute the user commands.
 */
public class Parser implements StringManipulation {
    /** List of valid command keyword */
    private static final ArrayList<String> COMMANDS = new ArrayList<String>(
            Arrays.asList("add", "list", "mark", "unmark", "delete", "find", "testing_mode")
    );


    /**
     * Checks if the input string is a valid command.
     *
     * @param keyWord The input string.
     * @return True if the input string is a valid keyword, False otherwise.
     */
    public boolean isCommand(String keyWord) {
        return COMMANDS.contains(keyWord);
    }

    /**
     * Adds a task to the TaskList based on information stored in the input string.
     * Returns an <code>InvalidCommand</code> object if taskInfo is null.
     * Returns an <code>InvalidCommand</code> object if the user keyed in an invalid task type.
     * Returns an <code>InvalidCommand</code> object if the user did not provide enough detail for the task type they
     * are adding.
     *
     * @param taskInfo Input string consisting of all relevant task information such as type, name, and relevant dates.
     * @param tasks The taskList containing all the tasks in Luke.
     * @return A Command object that adds a task to the taskList when executed.
     */
    private Command prepareAddTask(String taskInfo, TaskList tasks) {
        // Check if taskInfo is empty
        if (taskInfo == null) {
            return new InvalidCommand();
        }

        String taskType = StringManipulation.getFirstWord(taskInfo);

        //Check if the task type entered by the user is valid
        if (!tasks.isTaskType(taskType)) {
            return new InvalidCommand();
        }

        String taskDetail = StringManipulation.removeFirstWord(taskInfo);

        //Checks if the name of the task is empty
        if (taskDetail == null) {
            return new InvalidCommand();
        }

        String taskName = StringManipulation.getFirstDetail(taskDetail).trim();
        String taskDates = StringManipulation.removeFirstDetail(taskDetail);

        return new AddCommand(taskType, taskName, taskDates);
    }

    /**
     * @return A <code>Command</code> object that list out the contents of the taskList.
     */
    private Command prepareListTask() {
        return new ListCommand();
    }

    /**
     * Mark the specified task as completed.
     *
     * @param taskSerialNumber The serial number of the task to be marked.
     * @param tasks The taskList containing all the tasks in Luke.
     * @return A Command object that marks the task corresponding to taskSerialNumber when executed.
     */
    private Command prepareMarkTask(String taskSerialNumber, TaskList tasks) {
        if (taskSerialNumber == null) {
            return new InvalidCommand();
        }
        int serialNumber;
        try {
            serialNumber = Integer.parseInt(taskSerialNumber);
            if (tasks.isOutOfBounds(serialNumber)) {
                throw new InvalidIndexException();
            }
        } catch (NumberFormatException e) {
            return new InvalidCommand();
        } catch (InvalidIndexException e) {
            return new OutOfBoundsCommand();
        }
        return new MarkCommand(serialNumber);
    }

    /**
     * Unmarks the specified task as completed.
     *
     * @param taskSerialNumber The serial number of the task to be unmarked.
     * @param tasks The taskList containing all the tasks in Luke.
     * @return A Command object that unmarks the task corresponding to taskSerialNumber when executed.
     */
    private Command prepareUnmarkTask(String taskSerialNumber, TaskList tasks) {
        if (taskSerialNumber == null) {
            return new InvalidCommand();
        }
        int serialNumber;
        try {
            serialNumber = Integer.parseInt(taskSerialNumber);
            if (tasks.isOutOfBounds(serialNumber)) {
                throw new InvalidIndexException();
            }
        } catch (NumberFormatException e) {
            return new InvalidCommand();
        } catch (InvalidIndexException e) {
            return new OutOfBoundsCommand();
        }
        return new UnmarkCommand(serialNumber);
    }

    /**
     * Deletes the specified task.
     *
     * @param taskSerialNumber The serial number of the task to be unmarked.
     * @param tasks The taskList containing all the tasks in Luke.
     * @return A Command object that deletes the task corresponding to taskSerialNumber when executed.
     */
    private Command prepareDeleteTask(String taskSerialNumber, TaskList tasks) {
        if (taskSerialNumber == null) {
            return new InvalidCommand();
        }
        int serialNumber;
        try {
            serialNumber = Integer.parseInt(taskSerialNumber);
            if (tasks.isOutOfBounds(serialNumber)) {
                throw new InvalidIndexException();
            }
        } catch (NumberFormatException e) {
            return new InvalidCommand();
        } catch (InvalidIndexException e) {
            return new OutOfBoundsCommand();
        }
        return new DeleteCommand(serialNumber);
    }

    /**
     * Searches the taskList for any task containing the string toFind in its name.
     *
     * @param toFind They keyword to be searched.
     * @return A Command object that searches the taskList for tasks containing toFind in its name.
     */
    private Command prepareFindTask(String toFind) {
        return new FindCommand(toFind);
    }

    private Command prepareTestingMode() {
        return new TestingModeCommand();
    }

    /**
     * This function takes in the command keyword and description and executes the specified command.
     *
     * @param command The command keyword indicating the type of command to execute.
     * @param description The string specifying the details of the command.
     * @param tasks The taskList containing all the tasks in Luke.
     * @return A Command object that executes the command corresponding to the command keyword keyed in by the user.
     */
    private Command prepareCommand(String command, String description, TaskList tasks) {
        switch (command) {
        case "add":
            return prepareAddTask(description, tasks);
        case "list":
            return prepareListTask();
        case "mark":
            return prepareMarkTask(description, tasks);
        case "unmark":
            return prepareUnmarkTask(description, tasks);
        case "delete":
            return prepareDeleteTask(description, tasks);
        case "find":
            return prepareFindTask(description);
        default:
            return prepareTestingMode();
        }
    }

    /**
     * Returns the appropriate Command object based on the user input.
     * Returns a <code>EchoCommand</code> object if the first word is not a valid command keyword.
     *
     * @param fullCommand The full user input.
     * @param tasks The taskList containing all the tasks in Luke.
     * @return A Command objects that suits the user input.
     */
    public Command parse(String fullCommand, TaskList tasks) {
        String firstWord = StringManipulation.getFirstWord(fullCommand);
        if (!isCommand(firstWord)) {
            return new EchoCommand(fullCommand);
        }
        String description = StringManipulation.removeFirstWord(fullCommand);
        return prepareCommand(firstWord, description, tasks);
    }
}
