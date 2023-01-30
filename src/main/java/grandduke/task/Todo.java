package grandduke.task;

public class Todo extends Task {

    /**
     * Creates a new Todo object
     * 
     * @param taskDesc
     *            the description of the task
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
}
