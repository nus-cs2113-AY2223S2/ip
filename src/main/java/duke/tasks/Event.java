package duke.tasks;

import duke.exceptions.UserInputException;

import static duke.exceptions.UserInputException.inputExceptionType.*;

public class Event extends Task {
    protected String startTime;
    protected String endTime;
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public Event(String newTaskInfo) throws UserInputException {
        final String[] eventSplit = newTaskInfo.trim().split("/+", 3);
        if (eventSplit[0].equals("")){
            throw new UserInputException(EMPTY_TASK_DESCRIPTION,"event");
        } else if (eventSplit.length == 1) {
            throw new UserInputException(EMPTY_EVENT_START_TIME);
        }else if (eventSplit.length == 2) {
            throw new UserInputException(EMPTY_EVENT_END_TIME);
        }else{
            this.description = eventSplit[0];
            this.startTime = eventSplit[1].substring(5);
            this.endTime = eventSplit[2].substring(3);
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startTime + "to: " + endTime + ")";
    }
}
