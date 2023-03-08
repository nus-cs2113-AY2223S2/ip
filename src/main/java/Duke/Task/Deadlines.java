package Duke.Task;

import Duke.Exception.EmptyDeadlineException;
import Duke.Task.Task;

public class Deadlines extends Task {
    private static final int DEADLINE_INDEX = 9;
    private static final int COLON_INDEX = 2;
    private String endTime;
    private String taskLabel = "[D]";

    /**
     * Represents a Deadlines object which is identified by the type D. Contains description and deadline info.
     */
    public Deadlines(String input) throws EmptyDeadlineException {
        super(input.substring(DEADLINE_INDEX, input.indexOf('/') - 1)); // Sanitize input by removing "deadline" at the start
        super.setTaskLabel(taskLabel);
        endTime = "(" + getEndTime(input) + ")";
        if (input.substring(DEADLINE_INDEX, input.indexOf('/') - 1).isEmpty()) {
            throw new EmptyDeadlineException();
        }
    }

    // Method of StringBuffer operation taken from
    // https://www.geeksforgeeks.org/insert-a-string-into-another-string-in-java/
    /**
     * Returns the deadline of the task.
     *
     * @param input user input to be parsed.
     * @return deadline of the tasked from parsing.
     */
    @Override
    public String getEndTime(String input) {
        String deadline = input.substring(input.indexOf('/') + 1); // endTime of task is the string after '/'
        StringBuffer deadlineCorrectFormat = new StringBuffer(deadline); // convert to StringBuffer for inserting ':'
        deadlineCorrectFormat.insert(COLON_INDEX, ":");
        return deadlineCorrectFormat.toString();
    }

    @Override
    public String toString() {
        return this.taskLabel + this.mark + " " + this.description + " " + this.endTime;
    }
}
