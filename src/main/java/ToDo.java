public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public String getTaskStatus() {
        return "[T]" +super.getTaskStatus();
    }
}
