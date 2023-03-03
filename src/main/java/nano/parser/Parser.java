package nano.parser;

import nano.data.TaskList;
import nano.ui.Ui;
import nano.data.exception.NanoCommandException;
import nano.data.exception.NanoInputFormatException;

import java.util.Arrays;

public class Parser {
    private static final int COMMAND_INDEX = 0;
    private static final int TASK_NAME_INDEX = 1;

    private static final int TASK_TYPE_INDEX = 2;
    private static final int TASK_START_DATE_INDEX = 3;
    private static final int TASK_DUE_DATE_INDEX = 3;
    private static final int TASK_END_DATE_INDEX = 4;
    private static final int USER_INPUT_MAX_ARG_COUNT = 4;

    /**
     * Executes command entered by user.
     *
     * @param userInput Input entered by user.
     * @param tasks Task list
     * @throws NanoCommandException If unknown command is given.
     */
    public static void executeCommand(String userInput, TaskList tasks) throws NanoCommandException {
        String[] userInputs;
        try {
            userInputs = processInput(userInput);
        } catch (NanoInputFormatException inputException) {
            Ui.displayInputErrorMessage();
            return;
        }

        switch (userInputs[COMMAND_INDEX]) {
        case "list":
            Ui.displayTaskListMessage(tasks.getTaskList());
            break;
        case "add":
            tasks.addTask(Arrays.copyOfRange(userInputs, 1, USER_INPUT_MAX_ARG_COUNT + 1));
            break;
        case "delete":
            tasks.deleteTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "mark":
            tasks.markTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "unmark":
            tasks.unmarkTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "find":
            tasks.findTasks(userInputs[TASK_NAME_INDEX]);
            break;
        case "help":
            Ui.displayCommandList();
            break;
        case "exit":
            Ui.displayExitMessage();
            System.exit(0);
        default:
            throw new NanoCommandException();
        }
        Ui.printHorizontalLine();
    }

    /**
     * Returns processed input entered by user to separate command and the details.
     *
     * @param userInput Input entered by user.
     * @return Processed input.
     * @throws NanoInputFormatException If user input does not follow the correct format.
     */
    private static String[] processInput(String userInput) throws NanoInputFormatException {
        userInput = userInput.trim();
        if (!userInput.startsWith("/")) {
            throw new NanoInputFormatException();
        }
        userInput = userInput.replaceFirst("/", "");
        String[] userInputs = new String[USER_INPUT_MAX_ARG_COUNT + 1];
        String[] commandAndName = userInput.split("\\s+", 2);
        System.arraycopy(commandAndName, 0, userInputs, 0, commandAndName.length);

        if (commandAndName.length == 2) {
            processTaskDetails(userInput, userInputs);
        }
        return userInputs;
    }

    /**
     * Process tasks into its details based on its task type.
     *
     * @param userInput Input entered by user.
     * @param userInputs Array of Strings to store processed input.
     * @throws NanoInputFormatException If tasks details are in an invalid format.
     */
    private static void processTaskDetails(String userInput, String[] userInputs) throws NanoInputFormatException {
        if (isDeadline(userInput)) {
            getDeadline(userInput, userInputs);
        } else if (isEvent(userInput)) {
            getEvent(userInput, userInputs);
        } else if (userInput.contains("/")) {
            throw new NanoInputFormatException();
        } else {
            getTodo(userInputs);
        }
    }

    private static void getTodo(String[] userInputs) {
        userInputs[TASK_TYPE_INDEX] = "todo";
    }

    private static void getEvent(String userInput, String[] userInputs) {
        userInputs[TASK_TYPE_INDEX] = "event";
        userInputs[TASK_NAME_INDEX] = userInput.substring(userInput.indexOf(" "), userInput.indexOf("from/")).trim();
        userInputs[TASK_START_DATE_INDEX] = userInput.substring(userInput.indexOf("from/") + 5,
                userInput.indexOf("to/")).trim();
        userInputs[TASK_END_DATE_INDEX] = userInput.substring(userInput.indexOf("to/") + 3).trim();
    }

    private static void getDeadline(String userInput, String[] userInputs) {
        userInputs[TASK_TYPE_INDEX] = "deadline";
        userInputs[TASK_NAME_INDEX] = userInput.substring(userInput.indexOf(" "), userInput.indexOf("by/")).trim();
        userInputs[TASK_DUE_DATE_INDEX] = userInput.substring(userInput.indexOf("by/") + 3).trim();
    }

    private static boolean isDeadline(String task) {
        return task.contains("by/");
    }

    private static boolean isEvent(String task) {
        return task.contains("from/") && task.contains("to/");
    }
}