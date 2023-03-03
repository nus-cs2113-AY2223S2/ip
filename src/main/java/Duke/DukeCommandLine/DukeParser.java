package Duke.DukeCommandLine;

import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

public class DukeParser {
    private static String WRONG_TASK_FORMAT_MESSAGE =
            "☹ OOPS!!! The description of a task cannot be empty.";
    private static String WRONG_EVENT_FORMAT_MESSAGE =
            "Please use the format: " +
                    "event <task name> /from <event time from> /to <event time to>";
    private static String WRONG_DEADLINE_FORMAT_MESSAGE =
            "\"Please use the format: " +
                    "deadline <task name> /by <deadline time>";
    private static String COMMAND_TOKEN = " ";
    private static String DEADLINE_TOKEN = "/by";
    private static String EVENT_TOKEN_FROM = "/from";
    private static String EVENT_TOKEN_TO = "/to";

    /**
     * Parses the input from the user and returns a DukeCommandLineInput object.
     * @param commandLine the command line input by the user.
     * @return type of the command and the message of the command in a DukeCommandLineInput object.
     */
    public DukeCommandLineInput parse(String commandLine) {
        commandLine = commandLine.trim();
        if(!commandLine.contains(COMMAND_TOKEN)) {
            return new DukeCommandLineInput(commandLine, "");
        }
        String[] command = commandLine.split(" ");
        String lineRemaining = commandLine.substring(commandLine.indexOf(COMMAND_TOKEN)+1);
        lineRemaining = lineRemaining.trim();
        return new DukeCommandLineInput(command[0], lineRemaining);
    }

    /**
     * Parses the input from the user and returns a DukeTask object.
     * @param inputTask the message of the command to be processed.
     * @return a DukeTask object if the input is invalid, else throws a DukeException.
     * @throws DukeException if the input is invalid (the String is empty).
     */
    public DukeTask processTask(String inputTask) throws DukeException {
        if(inputTask.equals("")) {
            throw new DukeException(WRONG_TASK_FORMAT_MESSAGE);
        }
        return new DukeTask(inputTask);
    }

    /**
     * Parses the input from the user and returns a DukeDeadline object.
     * @param inputTask the message of the command to be processed.
     * @return a DukeDeadline object if the format of the input is invalid, else throws a DukeException.
     * @throws DukeException if the format of the input is invalid (the String does not contain "/by").
     */
    public DukeDeadline processDeadline(String inputTask) throws DukeException {
        try {
            String deadlineName = inputTask.substring(0, inputTask.indexOf(DEADLINE_TOKEN));
            deadlineName = deadlineName.trim();
            String deadlineTime = inputTask.substring(inputTask.indexOf(DEADLINE_TOKEN)+4);
            deadlineTime = deadlineTime.trim();
            return new DukeDeadline(deadlineName, deadlineTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(WRONG_DEADLINE_FORMAT_MESSAGE);
        }
    }

    /**
     * Parses the input from the user and returns a DukeEvent object.
     * @param inputTask the message of the command to be processed.
     * @return a DukeEvent object if the format of the input is invalid, else throws a DukeException.
     * @throws DukeException if the format of the input is invalid
     * (the String does not contain "/from" and "/to").
     */
    public DukeEvent processEvent(String inputTask) throws DukeException {
        try {
            String eventName = inputTask.substring(0, inputTask.indexOf(EVENT_TOKEN_FROM)-1);
            eventName = eventName.trim();
            String eventTimeFrom = inputTask.substring(inputTask.indexOf(EVENT_TOKEN_FROM)+6,
                    inputTask.indexOf(EVENT_TOKEN_TO)-1);
            eventTimeFrom = eventTimeFrom.trim();
            String eventTimeTo = inputTask.substring(inputTask.indexOf(EVENT_TOKEN_TO)+4);
            return new DukeEvent(eventName, eventTimeFrom, eventTimeTo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(WRONG_EVENT_FORMAT_MESSAGE);
        }
    }
}
