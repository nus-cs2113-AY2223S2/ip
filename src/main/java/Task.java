public class Task {
    private String name;
    private boolean completionStatus;

    public Task(String name) {
        this.name = name;
        this.completionStatus = false;
    }

    public String getName() {
        return this.name;
    }

    public void setStatus(boolean status) {
        this.completionStatus = status;
    }

    public boolean isDone() {
        return this.completionStatus;
    }
}
