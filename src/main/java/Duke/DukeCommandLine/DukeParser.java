package Duke.DukeCommandLine;

import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

public class DukeParser {
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
    public DukeTask processTask(String inputTask) throws DukeTaskInputException {
        if(inputTask.equals("")) {
            throw new DukeTaskInputException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        return new DukeTask(inputTask);
    }
    public DukeDeadline processDeadline(String inputTask) throws DukeTaskInputException {
        try {
            String deadlineName = inputTask.substring(0, inputTask.indexOf("/by"));
            deadlineName = deadlineName.trim();
            String deadlineTime = inputTask.substring(inputTask.indexOf("/by")+4);
            deadlineTime = deadlineTime.trim();
            return new DukeDeadline(deadlineName, deadlineTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeTaskInputException("\"Please use the format: " +
                    "deadline <task name> /by <deadline time>");
        }
    }
    public DukeEvent processEvent(String inputTask) throws DukeTaskInputException {
        try {
            String eventName = inputTask.substring(0, inputTask.indexOf("/from")-1);
            eventName = eventName.trim();
            String eventTimeFrom = inputTask.substring(inputTask.indexOf("/from")+6,
                    inputTask.indexOf("/to")-1);
            eventTimeFrom = eventTimeFrom.trim();
            String eventTimeTo = inputTask.substring(inputTask.indexOf("/to")+4);
            return new DukeEvent(eventName, eventTimeFrom, eventTimeTo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeTaskInputException("Please use the format: " +
                    "event <task name> /from <event time from> /to <event time to>");
        }
    }
}
