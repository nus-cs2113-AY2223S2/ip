package luke;

import luke.command.AddCommand;
import luke.command.Command;
import luke.command.DeleteCommand;
import luke.command.EchoCommand;
import luke.command.InvalidCommand;
import luke.command.ListCommand;
import luke.command.MarkCommand;
import luke.command.OutOfBoundsCommand;
import luke.command.TestingModeCommand;
import luke.command.UnmarkCommand;
import luke.exception.InvalidIndexException;
import luke.task.StringManipulation;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser implements StringManipulation {
    private static final ArrayList<String> COMMANDS = new ArrayList<String>(
            Arrays.asList("add", "list", "mark", "unmark", "delete", "testing_mode")
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
     *
     * @param taskInfo Input string consisting of all relevant task information such as type, name, and relevant dates.
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

        String taskName = StringManipulation.getFirstWord(taskDetail);
        String taskDates = StringManipulation.removeFirstWord(taskDetail);

        return new AddCommand(taskType, taskName, taskDates);
    }

    private Command prepareListTask() {
        return new ListCommand();
    }

    /**
     * Mark the specified task as completed.
     *
     * @param taskSerialNumber The serial number of the task to be marked.
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
     * Unmarks the specified task.
     *
     * @param taskSerialNumber The serial number of the task to be unmarked.
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

    private Command prepareTestingMode() {
        return new TestingModeCommand();
    }

    /**
     * This function takes in the command keyword and description and executes the specified command.
     *
     * @param command The command keyword indicating the type of command to execute.
     * @param description The string specifying the details of the command.
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
        default:
            return prepareTestingMode();
        }
    }

    public Command parse(String fullCommand, TaskList tasks) {
        String firstWord = StringManipulation.getFirstWord(fullCommand);
        if (!isCommand(firstWord)) {
            return new EchoCommand(fullCommand);
        }
        String description = StringManipulation.removeFirstWord(fullCommand);
        return prepareCommand(firstWord, description, tasks);
    }
}
