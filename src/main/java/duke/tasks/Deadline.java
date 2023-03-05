package duke.tasks;

import duke.exceptions.UserInputException;

import static duke.exceptions.UserInputException.inputExceptionType.EMPTY_DEADLINE_TIME;
import static duke.exceptions.UserInputException.inputExceptionType.EMPTY_TASK_DESCRIPTION;

//initial skeleton adapted from  https://nus-cs2113-ay2223s2.github.io/website/schedule/week4/project.html partial solution
public class Deadline extends Task {

    protected String by;

    public String getBy() {
        return by;
    }

    public Deadline(String newTaskInfo) throws UserInputException {
        final String[] deadlineSplit = newTaskInfo.trim().split("/by", 2);
        if (deadlineSplit[0].equals("")){
            throw new UserInputException(EMPTY_TASK_DESCRIPTION,"deadline");
        } else if (deadlineSplit.length == 1) {
            throw new UserInputException(EMPTY_DEADLINE_TIME);
        }else{
            this.description = deadlineSplit[0];
            this.by = deadlineSplit[1];
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}