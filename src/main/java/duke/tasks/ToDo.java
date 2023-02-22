package duke.tasks;

public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param taskName description of task.
     */
    public ToDo(String taskName) {
        super(taskName);
        super.type = "[T]";
    }

    @Override
    public String toString() {
        return checkBoxOutput() + this.taskName;
    }
}
