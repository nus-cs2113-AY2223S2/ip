package Duke.Tasks;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    /***
     * Outputs task type
     * @return String representing task type.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /***
     * Outputs a formatted String containing information of the task saved in a text file.
     * @return String containing information of the task.
     */
    @Override
    public String saveInfo() {
        return getTaskType() + "t/" +
                (isDone ? "X" : "Y") + "m/" +
                taskName + "\n";
    }
}
