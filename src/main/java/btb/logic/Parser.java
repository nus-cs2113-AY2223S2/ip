package btb.logic;

import btb.exceptions.EmptyTaskDescriptionException;
import btb.exceptions.EmptyTaskNumberException;
import btb.exceptions.InvalidDeadlineCommandException;
import btb.exceptions.InvalidEventCommandException;
import btb.exceptions.NotIntegerTaskNumberException;
import btb.exceptions.TaskNumberOutOfBoundException;
import btb.tasks.TaskManager;

public class Parser {

    /**
     * splits the string into an array
     * consisting of [command, description].
     *
     * @param string input entered by user
     * @return string array consisting of the split inputs
     */
    public static String[] handleInput(String string) {
        return string.split(" ", 2);
    }

    /**
     * breaks down description into
     * [task description, dueDate].
     *
     * @param description description entered by user
     * @return string array consisting of the split inputs
     */
    public static String[] handleDeadline(String description) throws
            InvalidDeadlineCommandException, EmptyTaskDescriptionException {
        if (!description.contains(" /by ")) {
            throw new InvalidDeadlineCommandException();
        }

        String[] splitStrings;
        splitStrings = description.split(" /", 2);
        splitStrings[1] = splitStrings[1].substring(3);

        if (splitStrings[0].trim().equals("")) {
            throw new EmptyTaskDescriptionException();
        }

        return splitStrings;
    }

    /**
     * breaks down the description into
     * [task description, startDate, endDate].
     *
     * @param description description entered by user
     * @return string array consisting of the split inputs
     */
    public static String[] handleEvent(String description) throws
            InvalidEventCommandException, EmptyTaskDescriptionException {
        if (!description.contains(" /from ") || !description.contains(" /to ")) {
            throw new InvalidEventCommandException();
        }
        String[] splitStrings = description.split(" /", 3);
        splitStrings[1] = splitStrings[1].substring(5);
        splitStrings[2] = splitStrings[2].substring(3);

        if (splitStrings[0].trim().equals("")) {
            throw new EmptyTaskDescriptionException();
        }

        return splitStrings;
    }

    public static int handleMark(TaskManager tasks, String description) throws
            TaskNumberOutOfBoundException, NotIntegerTaskNumberException, EmptyTaskNumberException {
        int taskNumber;

        if (description.equals("")) {
            throw new EmptyTaskNumberException();
        }

        taskNumber = convertStringToInt(description);
        boolean isOutOfBound = taskNumber < 1 || taskNumber > tasks.getSize();

        if (isOutOfBound) {
            throw new TaskNumberOutOfBoundException(taskNumber);
        }

        return taskNumber;
    }

    private static boolean isInteger(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) < '0' || string.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }

    private static int convertStringToInt(String string) throws NotIntegerTaskNumberException {
        int number;
        if (isInteger(string)) {
            number = Integer.parseInt(string);
        } else {
            throw new NotIntegerTaskNumberException(string);
        }
        return number;
    }

    public static String[] handleTextInputs(String description) {
        String[] taskDescriptions = description.split("] ");
        String[] output = new String[3];
        output[1] = taskDescriptions[1];

        if (taskDescriptions[0].contains("✔")) {
            output[2] = "done";
        } else {
            output[2] = "undone";
        }

        if (taskDescriptions[0].contains("[T]")) {
            output[0] = "todo";

        } else if (taskDescriptions[0].contains("[D]")) {
            output[0] = "deadline";
        } else if (taskDescriptions[0].contains("E")){
            output[0] = "event";
        } else {
            output[0] = "wrongFormat";
        }

        return output;
    }

    public static String[] handleTextDeadlineInputs(String description) {
        String[] taskDescription = description.split("by: ");
        taskDescription[0] = taskDescription[0].substring(0, taskDescription[0].length() - 2);
        taskDescription[1] = taskDescription[1].substring(0, taskDescription[1].indexOf(")"));

        return taskDescription;
    }

    public static String[] handleTextEventInputs(String description) {
        String[] taskDescription = description.split(": ");
        taskDescription[0] = taskDescription[0].substring(0, taskDescription[0].indexOf(" (from"));
        taskDescription[1] = taskDescription[1].substring(0, taskDescription[1].indexOf(" to"));
        taskDescription[2] = taskDescription[2].substring(0, taskDescription[2].indexOf(")"));

        return taskDescription;
    }
}
