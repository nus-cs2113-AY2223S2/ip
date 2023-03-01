package duke;

public class Parser {
    // probably put into parser
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
            taskInformation = userInput.split(Duke.SPACE_DELIMITER, 2)[1]; // taskInformation is the task number (1-indexed).
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
                taskInformation = userInput.split(Duke.SPACE_DELIMITER, 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                informationNeededForPerformingUserRequest[1] = taskInformation;
            } else if (command.equals("deadline")) {
                taskInformation = userInput.split(Duke.SPACE_DELIMITER, 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                // For "deadline": informationNeeded...[1] is the name, [2] is the deadline.
                informationNeededForPerformingUserRequest[1] = taskInformation.split(Duke.DEADLINE_BY_DELIMITER)[0];
                informationNeededForPerformingUserRequest[2] = taskInformation.split(Duke.DEADLINE_BY_DELIMITER)[1];
            } else if (command.equals("event")) {
                taskInformation = userInput.split(Duke.SPACE_DELIMITER, 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                // For "event": informationNeeded...[1] is the name, [2] is when the task starts, [3] is when the task ends
                informationNeededForPerformingUserRequest[1] = taskInformation.substring(0, taskInformation.indexOf(Duke.EVENT_FROM_DELIMITER));
                informationNeededForPerformingUserRequest[2] = taskInformation.substring(taskInformation.indexOf(Duke.EVENT_FROM_DELIMITER) + Duke.EVENT_START_INDEX, taskInformation.indexOf(Duke.EVENT_TO_DELIMITER));
                informationNeededForPerformingUserRequest[3] = taskInformation.substring(taskInformation.indexOf(Duke.EVENT_TO_DELIMITER) + Duke.EVENT_END_INDEX);
            }
        } catch (IndexOutOfBoundsException e) {
            informationNeededForPerformingUserRequest[0] = "error with information provided";
        }
        return informationNeededForPerformingUserRequest;
    }
}
