package duke;

import static java.lang.Character.getNumericValue;

/**
 * Class containing methods for parsing the user's inputs at the command line interface as well as parsing deadlines
 * to convert them into formats suitable for loading and adding deadline tasks to the task list.
 */
public class Parser {
    private static final String SLASH_DELIMITER = "/";
    private static final String DEADLINE_BY_DELIMITER = " /by ";
    private static final String EVENT_FROM_DELIMITER = " /from ";
    private static final String EVENT_TO_DELIMITER = " /to ";
    private static final String SPACE_DELIMITER = " ";
    private static final int HOUR_END_INDEX = 2;
    private static final int EVENT_START_INDEX = 7;
    private static final int EVENT_END_INDEX = 5;
    private static final int NUMBER_OF_CHARACTERS_USED_BY_AM_PM = 3;
    private static final int TIME_CONVERSION_PM_OFFSET = 12;
    private static final int TIME_FORMAT_STANDARD_LENGTH_INCLUDING_AM_PM = 11;
    private static final int TIME_FORMAT_STANDARD_LENGTH_EXCLUDING_AM_PM = 8;

    /**
     * Parses the user's inputs to find out what the user wants to do (and parses deadlines from the saved file to
     * convert them to a format which can be used to load deadline tasks to the task list).
     *
     * @param userInput The string containing the user's input at the command line interface.
     * @return An array of strings which contain key information about what the user wants to do.
     * @throws IndexOutOfBoundsException If the user's input does not contain enough information for Duke to understand
     *                                   what the user wants to do.
     */
    public static String[] parseUserInput(String userInput) throws IndexOutOfBoundsException {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        String command = userInput.split(" ", 2)[0];
        switch (command) {
        case "bye":
            // Fallthrough
        case "list":
            informationNeededForPerformingUserRequest[0] = command;
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            informationNeededForPerformingUserRequest = parseModificationRequest(userInput, command);
            break;
        case "todo":
            // Fallthrough
        case "event":
            // Fallthrough
        case "deadline":
            informationNeededForPerformingUserRequest = parseTaskAdditionRequest(command, userInput);
            break;
        case "find":
            informationNeededForPerformingUserRequest = parseFindRequest(userInput);
            break;
        default:
            informationNeededForPerformingUserRequest[0] = "invalid command";
            break;
        }
        return informationNeededForPerformingUserRequest;
    }

    /**
     * Parses the user's inputs to find out what modification (delete, mark or unmark) to the task list that the user
     * wants Duke to do.
     *
     * @param userInput The string containing the user's input at the command line interface.
     * @param command   The type of modification to the task list that the user wants to do.
     * @return An array of strings which contain key information about what modification to the task list the user
     * wants to do.
     */
    public static String[] parseModificationRequest(String userInput, String command) {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        try {
            // for delete/mark/unmark: taskInformation is the task number (1-indexed).
            String taskInformation = userInput.split(SPACE_DELIMITER, 2)[1];
            informationNeededForPerformingUserRequest[0] = command;
            informationNeededForPerformingUserRequest[1] = taskInformation;
        } catch (IndexOutOfBoundsException e) {
            informationNeededForPerformingUserRequest[0] = "error with information provided";
        }
        return informationNeededForPerformingUserRequest;
    }

    /**
     * Parses deadlines from the user's input to convert them to a format which can be used to add deadline tasks to
     * the task list.
     *
     * @param deadline The string containing the deadline of a deadline task provided by the user.
     * @return A string containing the reformatted deadline which can be used to add deadline tasks to the task
     * list.
     */
    public static String parseDeadlineInput(String deadline) {
        String[] deadlineSplitUsingSlash = deadline.split(SLASH_DELIMITER, 3);
        String day = deadlineSplitUsingSlash[0];
        if (day.length() == 1) {
            day = "0" + day; // pad 0 to single digit day
        }
        String month = deadlineSplitUsingSlash[1];
        if (month.length() == 1) {
            month = "0" + month; // pad 0 to single digit month
        }
        String year = deadlineSplitUsingSlash[2].split(SPACE_DELIMITER, 2)[0];
        String time = deadlineSplitUsingSlash[2].split(SPACE_DELIMITER, 2)[1];
        String hour = time.substring(0, HOUR_END_INDEX);
        String minute = time.substring(HOUR_END_INDEX);

        time = year + "-" + month + "-" + day + "T" + hour + ":"
                + minute + ":" + "00"; // convert time to yyyy-mm-ddTHH:MM:00 format
        return time;
    }

    /**
     * Parses deadlines from the local save file to convert them to a format which can be used to load deadline tasks to
     * the task list.
     *
     * @param deadline The string containing the deadline of a deadline task saved in user's local save file.
     * @return A string containing the reformatted deadline which can be used in loading saved deadline tasks to the
     * task list.
     */
    public static String parseSavedDeadline(String deadline) {
        String[] deadlineSplitBySpace = deadline.split(SPACE_DELIMITER, 4);
        String year = deadlineSplitBySpace[2].replace(",", "");
        String month;
        switch (deadlineSplitBySpace[1]) {
        case "Jan":
            month = "01";
            break;
        case "Feb":
            month = "02";
            break;
        case "Mar":
            month = "03";
            break;
        case "Apr":
            month = "04";
            break;
        case "May":
            month = "05";
            break;
        case "Jun":
            month = "06";
            break;
        case "Jul":
            month = "07";
            break;
        case "Aug":
            month = "08";
            break;
        case "Sep":
            month = "09";
            break;
        case "Oct":
            month = "10";
            break;
        case "Nov":
            month = "11";
            break;
        case "Dec":
            month = "12";
            break;
        default:
            month = "XX";
            System.out.println("Something went wrong when converting the month of a deadline!");
            break;
        }
        String day = deadlineSplitBySpace[0];
        if (day.length() == 1) {
            day = "0" + day;
        }
        String time = deadlineSplitBySpace[3];
        if (time.length() < TIME_FORMAT_STANDARD_LENGTH_INCLUDING_AM_PM) {
            char indicatorOfAMorPM = time.charAt(time.length() - NUMBER_OF_CHARACTERS_USED_BY_AM_PM + 1);
            if (indicatorOfAMorPM == 'A') {
                time = "0" + time;
                time = time.substring(0, TIME_FORMAT_STANDARD_LENGTH_EXCLUDING_AM_PM);
            } else if (indicatorOfAMorPM == 'P') {
                int hour = getNumericValue(time.charAt(0)) + TIME_CONVERSION_PM_OFFSET;
                time = hour + time.substring(1, time.length() - NUMBER_OF_CHARACTERS_USED_BY_AM_PM);
            }
        } else {
            time = time.substring(0, TIME_FORMAT_STANDARD_LENGTH_EXCLUDING_AM_PM);
        }
        return year + "-" + month + "-" + day + "T" + time;
    }

    /**
     * Parses the user's inputs to find out the information needed to add the task (todo/deadline/event), that the
     * user wants, to the task list.
     *
     * @param command   The type of task (todo/deadline/event) that the user wants to add to the task list.
     * @param userInput The string containing the user's input at the command line interface.
     * @return An array of strings which contain key information about what addition to the task list the user
     * wants Duke to do.
     * If a todo task is to be added: The string at index 1 of this array contains the task name provided by the user.
     * If a deadline task is to be added: The string at index 1 of this array contains the task name provided by the
     * user. The string at index 2 of this array contains the deadline provided by the user.
     * If an event task is to be added: The string at index 1 of this array contains the task name provided by the user.
     * The string at index 2 of this array contains the start time provided by the user. The string at index 3 of this
     * array contains the end time provided by the user.
     */
    public static String[] parseTaskAdditionRequest(String command, String userInput) {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        String taskInformation;
        try {
            if (command.equals("todo")) {
                taskInformation = userInput.split(SPACE_DELIMITER, 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                informationNeededForPerformingUserRequest[1] = taskInformation;
            } else if (command.equals("deadline")) {
                taskInformation = userInput.split(SPACE_DELIMITER, 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                // For "deadline": informationNeeded...[1] is the name, [2] is the deadline.
                informationNeededForPerformingUserRequest[1] = taskInformation.split(DEADLINE_BY_DELIMITER)[0];
                String deadline = taskInformation.split(DEADLINE_BY_DELIMITER)[1];
                informationNeededForPerformingUserRequest[2] = parseDeadlineInput(deadline);
            } else if (command.equals("event")) {
                taskInformation = userInput.split(SPACE_DELIMITER, 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                // For "event": informationNeeded...[1] is the name, [2] is when the task starts, [3] is when the task ends
                informationNeededForPerformingUserRequest[1] = taskInformation.substring(0, taskInformation
                        .indexOf(EVENT_FROM_DELIMITER));
                informationNeededForPerformingUserRequest[2] = taskInformation.substring(taskInformation
                                .indexOf(EVENT_FROM_DELIMITER) + EVENT_START_INDEX,
                        taskInformation.indexOf(EVENT_TO_DELIMITER));
                informationNeededForPerformingUserRequest[3] = taskInformation.substring(taskInformation
                        .indexOf(EVENT_TO_DELIMITER) + EVENT_END_INDEX);
            }
        } catch (IndexOutOfBoundsException e) {
            informationNeededForPerformingUserRequest[0] = "error with information provided";
        }
        return informationNeededForPerformingUserRequest;
    }

    /**
     * Parses the user's inputs to find out the keywords included in the tasks that the user wants to find in the task
     * list.
     *
     * @param userInput The string containing the user's input at the command line interface.
     * @return An array of strings which contain key information about what tasks the user wants to find in the task
     * list. The string at index 1 of this array contains the keywords provided by the user.
     */
    public static String[] parseFindRequest(String userInput) {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        informationNeededForPerformingUserRequest[0] = "find";
        informationNeededForPerformingUserRequest[1] = userInput.split(SPACE_DELIMITER, 2)[1];
        return informationNeededForPerformingUserRequest;
    }
}
