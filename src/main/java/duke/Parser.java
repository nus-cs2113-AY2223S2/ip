package duke;

public class Parser {
    static final String DEADLINE_BY_DELIMITER = " /by ";
    static final String EVENT_FROM_DELIMITER = " /from ";
    static final String EVENT_TO_DELIMITER = " /to ";
    static final int EVENT_START_INDEX = 7;
    static final int EVENT_END_INDEX = 5;
    static final String SPACE_DELIMITER = " ";
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
        case "find":
            informationNeededForPerformingUserRequest = parseFindRequest(userInput, command);
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
                informationNeededForPerformingUserRequest[2] = taskInformation.split(DEADLINE_BY_DELIMITER)[1];
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

    public static String[] parseFindRequest(String userInput, String command) {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        informationNeededForPerformingUserRequest[0] = command;
        informationNeededForPerformingUserRequest[1] = userInput.split(SPACE_DELIMITER,2)[1];
        return informationNeededForPerformingUserRequest;
    }
}
