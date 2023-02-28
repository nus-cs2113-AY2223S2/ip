package Duke.DukeCommandLine;

import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

public class DukeParser {
    /**
     * Parses the input from the user and returns a DukeCommandLineInput object.
     * @param commandLine the command line input by the user.
     * @return type of the command and the message of the command in a DukeCommandLineInput object.
     */
    public DukeCommandLineInput parse(String commandLine) {
        commandLine = commandLine.trim();
        if(!commandLine.contains(" ")) {
            return new DukeCommandLineInput(commandLine, "");
        }
        String[] command = commandLine.split(" ");
        String lineRemaining = commandLine.substring(commandLine.indexOf(" ")+1);
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
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
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
            String deadlineName = inputTask.substring(0, inputTask.indexOf("/by"));
            deadlineName = deadlineName.trim();
            String deadlineTime = inputTask.substring(inputTask.indexOf("/by")+4);
            deadlineTime = deadlineTime.trim();
            return new DukeDeadline(deadlineName, deadlineTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\"Please use the format: " +
                    "deadline <task name> /by <deadline time>");
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
            String eventName = inputTask.substring(0, inputTask.indexOf("/from")-1);
            eventName = eventName.trim();
            String eventTimeFrom = inputTask.substring(inputTask.indexOf("/from")+6,
                    inputTask.indexOf("/to")-1);
            eventTimeFrom = eventTimeFrom.trim();
            String eventTimeTo = inputTask.substring(inputTask.indexOf("/to")+4);
            return new DukeEvent(eventName, eventTimeFrom, eventTimeTo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please use the format: " +
                    "event <task name> /from <event time from> /to <event time to>");
        }
    }
}
