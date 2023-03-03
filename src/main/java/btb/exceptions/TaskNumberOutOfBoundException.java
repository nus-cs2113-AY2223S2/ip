package btb.exceptions;

public class TaskNumberOutOfBoundException extends DukeException {
    private final int taskNumber;
    public TaskNumberOutOfBoundException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String getMessage() {
        return "\t Task " + taskNumber + " does not exists (>__<).\n" +
                "\t Please try again!";
    }
}
