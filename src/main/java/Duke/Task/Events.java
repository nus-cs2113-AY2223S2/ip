package Duke.Task;

import Duke.Exception.EmptyEventsException;

public class Events extends Task {
    private static final int EVENT_INDEX = 6;
    private static final int FIRST_COLON_INDEX = 4;
    private static final int SECOND_COLON_INDEX = 2;
    private String timeLine;
    private String taskLabel = "[E]";
    public Events(String input) throws EmptyEventsException {
        super(input.substring(EVENT_INDEX, input.indexOf('/') - 1));
        super.setTaskLabel(taskLabel);
        timeLine = "(" + getStartTime(input) + getEndTime(input) + ")";
        if (input.substring(EVENT_INDEX, input.indexOf('/') - 1).isEmpty()) {
            throw new EmptyEventsException();
        }
    }

    public String[] splitInput(String input) {
        String[] inputAfterSplit = input.split("/", 3); // split twice to generate three strings
        return inputAfterSplit;
    }

    @Override
    public String getStartTime(String input) {
        String[] inputAfterSplit = splitInput(input);
        String startTime = inputAfterSplit[1];
        StringBuffer startTimeCorrectFormat = new StringBuffer(startTime);
        startTimeCorrectFormat.insert(FIRST_COLON_INDEX, ":");
        return startTimeCorrectFormat.toString();
    }

    @Override
    public String getEndTime(String input) {
        String[] inputAfterSplit = splitInput(input);
        String startTime = inputAfterSplit[2];
        StringBuffer endTimeCorrectFormat = new StringBuffer(startTime); // convert to StringBuffer for inserting ':'
        endTimeCorrectFormat.insert(SECOND_COLON_INDEX, ":");
        return endTimeCorrectFormat.toString();
    }

    @Override
    public String toString() {
        return this.taskLabel + this.mark + " " + this.description + " " + this.timeLine;
    }
}
