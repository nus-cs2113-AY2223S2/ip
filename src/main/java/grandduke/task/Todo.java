package grandduke.task;

public class Todo extends Task {

    /**
     * Creates a new Todo object
     * @param taskDesc the description of the task
     */
    public Todo(String taskDesc) {
        super(taskDesc);
    }

    /**
     * Returns the string representation of the Todo object
     */
    @Override
    public String getTaskPrint() {
        return "[T]" + super.getTaskPrint();
    }

    /**
     * Returns the string representation of the Todo object to be saved in the data
     */
    @Override
    public String getTaskSaveString() {
        return "todo | " + (super.getIsDone() ? "1" : "0") + " | " + super.getTaskDesc();
    }
}
