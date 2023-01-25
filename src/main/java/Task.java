public class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
