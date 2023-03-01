package duke;

import static java.lang.Character.getNumericValue;

public class Parser {
    static final String SLASH_DELIMITER = "/";
    static final String DEADLINE_BY_DELIMITER = " /by ";
    static final String EVENT_FROM_DELIMITER = " /from ";
    static final String EVENT_TO_DELIMITER = " /to ";
    static final int HOUR_END_INDEX = 2;
    static final int EVENT_START_INDEX = 7;
    static final int EVENT_END_INDEX = 5;
    static final String SPACE_DELIMITER = " ";
    static final int NUMBER_OF_CHARACTERS_USED_BY_AM_PM = 3;
    static final int TIME_CONVERSION_PM_OFFSET = 12;

    static final int TIME_FORMAT_STANDARD_LENGTH_INCLUDING_AM_PM = 11;
    static final int TIME_FORMAT_STANDARD_LENGTH_EXCLUDING_AM_PM = 8;

    public static String[] parseUserInput(String userInput) throws IndexOutOfBoundsException {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        String taskInformation = "";
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
            informationNeededForPerformingUserRequest = parseModificationRequest(taskInformation, userInput, command);
            break;
        case "todo":
            // Fallthrough
        case "event":
            // Fallthrough
        case "deadline":
            informationNeededForPerformingUserRequest = parseTaskAdditionRequest(command, taskInformation, userInput);
            break;
        default:
            informationNeededForPerformingUserRequest[0] = "invalid command";
            break;
        }
        return informationNeededForPerformingUserRequest;
    }

    public static String[] parseModificationRequest(String taskInformation, String userInput, String command) {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        try {
            taskInformation = userInput.split(SPACE_DELIMITER, 2)[1]; // taskInformation is the task number (1-indexed).
            informationNeededForPerformingUserRequest[0] = command;
            informationNeededForPerformingUserRequest[1] = taskInformation;
        } catch (IndexOutOfBoundsException e) {
            informationNeededForPerformingUserRequest[0] = "error with information provided";
        }
        return informationNeededForPerformingUserRequest;
    }

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

        // update time to be in yyyy-mm-ddTHH:MM:00 format
        time = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + "00";
        return time;
    }

    public static String parseSavedDeadline(String deadline) {
        // supposedly formatted to: 2 Dec 2019, 6:00:00PM
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
                time = Integer.toString(hour) + time.substring(1, time.length() - NUMBER_OF_CHARACTERS_USED_BY_AM_PM);
            }
        } else { // correct length but need truncate the AM/PM still
            time = time.substring(0, TIME_FORMAT_STANDARD_LENGTH_EXCLUDING_AM_PM);
        }
        String formattedDeadline = year + "-" + month + "-" + day + "T" + time;
        return formattedDeadline;
    }

    public static String[] parseTaskAdditionRequest(String command, String taskInformation, String userInput) {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
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
                // need to work with this part!

            } else if (command.equals("event")) {
                taskInformation = userInput.split(SPACE_DELIMITER, 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                // For "event": informationNeeded...[1] is the name, [2] is when the task starts, [3] is when the task ends
                informationNeededForPerformingUserRequest[1] = taskInformation.substring(0, taskInformation.indexOf(EVENT_FROM_DELIMITER));
                informationNeededForPerformingUserRequest[2] = taskInformation.substring(taskInformation.indexOf(EVENT_FROM_DELIMITER) + EVENT_START_INDEX, taskInformation.indexOf(EVENT_TO_DELIMITER));
                informationNeededForPerformingUserRequest[3] = taskInformation.substring(taskInformation.indexOf(EVENT_TO_DELIMITER) + EVENT_END_INDEX);
            }
        } catch (IndexOutOfBoundsException e) {
            informationNeededForPerformingUserRequest[0] = "error with information provided";
        }
        return informationNeededForPerformingUserRequest;
    }
}
