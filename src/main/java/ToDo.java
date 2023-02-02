public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
        super.taskType = TaskType.TO_DO;
    }
}
