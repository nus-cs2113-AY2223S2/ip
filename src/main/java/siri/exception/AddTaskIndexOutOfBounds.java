package siri.exception;

public class AddTaskIndexOutOfBounds extends Exception {
    protected String taskType;

    public AddTaskIndexOutOfBounds(String taskType) {
        this.taskType = taskType;
    }

    public void printError() {
        System.out.println("OPPS!!! The description of a " + taskType + " cannot be empty.");
    }

}
