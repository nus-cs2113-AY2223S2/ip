public class Task {

    public String taskName;
    public String status;

    public Task(String taskName) {
        this.taskName = taskName;
        status = " ";
    }

    public void setStatusChar(String statusChar) {
        status = statusChar;
    }
}
