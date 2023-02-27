package siri.exception;

/**
 * Signals an error caused by not entering a task description.
 */
public class AddTaskIndexOutOfBounds extends Exception {
    protected String taskType;

    public AddTaskIndexOutOfBounds(String taskType) {
        this.taskType = taskType;
    }

    public String printError() {
        String errorMessage = "OPPS!!! The description of a " + taskType + " cannot be empty.";
        return errorMessage;
    }

}
